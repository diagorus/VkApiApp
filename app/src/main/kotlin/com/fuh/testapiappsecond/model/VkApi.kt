package com.fuh.testapiappsecond.model

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface VkApi {
    @GET("friends.get?order=random")
    fun getFriends(@Query("user_id") userId: Int,
                   @Query("offset") offset: Int,
                   @Query("count") count: Int,
                   @Query("fields") fields: String): Observable<ApiModels.FriendsResponse>

    @GET("database.getCitiesById")
    fun getCitiesById(@Query("city_ids") cids: String): Observable<ApiModels.CitiesResponse>
}