package com.fuh.testapiappsecond.presenter

import android.content.Context

interface Presenter<T> {
    fun init(view: T)
}