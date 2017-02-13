package com.fuh.testapiappsecond.presenter

import android.util.Log
import com.fuh.testapiappsecond.model.ApiModels
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .flatMap { Observable.from(it.response) }
                .map {

                    FriendMapper().map(it)
                }
                .toList()
                .subscribe (
                        { view.showFriends(it) },
                        { Log.e(TAG, Log.getStackTraceString(it))})
    }
}