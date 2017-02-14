package com.fuh.testapiappsecond.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class VkRepository {
    private val vkApi: VkApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        vkApi = retrofit.create(VkApi::class.java)
    }

    fun getFriends(userId: Int, offset: Int, count: Int, fields: String):
            Observable<ApiModels.FriendsResponse> {
        return vkApi.getFriends(userId, offset, count, fields)
    }

    fun getCitiesById(cityIds: Set<Int>):
            Observable<ApiModels.CitiesResponse> {
        return vkApi.getCitiesById(cityIds.joinToString(","))
    }
}