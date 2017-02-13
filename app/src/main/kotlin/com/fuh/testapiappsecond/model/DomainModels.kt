package com.fuh.testapiappsecond.model

object DomainModels {
    data class Friend(val id: Int,
                      val fullName: String,
                      val city: String,
                      val domain: String,
                      val photoUrl: String,
                      val isOnline: Boolean)
}