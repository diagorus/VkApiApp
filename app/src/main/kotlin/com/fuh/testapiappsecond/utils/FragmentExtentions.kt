package com.fuh.testapiappsecond.utils

import android.app.Fragment
import android.content.Context
import android.widget.Toast

val Fragment.ctx: Context
    get() = activity
fun Fragment.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(ctx, text, duration).show()
}