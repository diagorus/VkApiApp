package com.fuh.testapiappsecond.presenter

import android.util.Log
import com.fuh.testapiappsecond.model.VkRepository
import com.fuh.testapiappsecond.model.DomainModels
import com.fuh.testapiappsecond.model.FriendMapper
import com.fuh.testapiappsecond.view.general.VkFriendsView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class VkFriendsPresenter(val view: VkFriendsView, val model: VkRepository){

    companion object {
        val TAG: String = VkFriendsPresenter::class.java.name
    }

    fun onFriendClicked(friend: DomainModels.Friend) {
        view.showInfo("${friend.fullName} is ${if (friend.isOnline) "online" else "offline"}")
    }

    fun loadFriends(offset: Int, count: Int) {
        model.getFriends(106478662 ,offset, count, "city,domain,photo_100,online")
                .subscribeOn(Schedulers.newThread())
                .flatMap { Observable.from(it.response) }
                .toList()
                .observeOn(Schedulers.newThread())
                .flatMap { friends ->
                    val cityIds = friends.map { it.cityId }.toSet()
                    model.getCitiesById(cityIds)
                            .map { it.response.associateBy ({it.cid}, {it.name}) }
                            .flatMap { citiesMap ->
                                Observable.from(
                                    friends.map {
                                        with(it) {
                                        //TODO: use tring resource instead of HARDCODED one
                                            DomainModels.Friend(uid, "$lastName $firstName",
                                                    citiesMap[cityId] ?: "Unknown city",
                                                    domain, photo50, online == 1)
                                        }
                                    }
                            ) }
                }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { view.showFriends(it) },
                        { Log.e(TAG, Log.getStackTraceString(it))})
    }
}