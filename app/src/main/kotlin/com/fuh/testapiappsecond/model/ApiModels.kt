package com.fuh.testapiappsecond.model

import com.google.gson.annotations.SerializedName
object ApiModels {
    data class FriendsResponse(@SerializedName("response") val response: List<Friend>)

    data class Friend(
            @SerializedName("uid") val uid: Int,
            @SerializedName("first_name") val firstName: String,
            @SerializedName("last_name") val lastName: String,
            @SerializedName("domain") val domain: String,
            @SerializedName("city") val cityId: Int,
            @SerializedName("photo_100") val photo50: String,
            @SerializedName("online") val online: Int)

    data class CitiesResponse(@SerializedName("response") val response: List<City>)

    data class City(
            @SerializedName("cid") val cid: Int,
            @SerializedName("title") val title: String)
}