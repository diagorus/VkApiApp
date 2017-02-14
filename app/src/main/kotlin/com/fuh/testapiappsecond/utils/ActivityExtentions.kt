package com.fuh.testapiappsecond.utils

import android.app.Activity
import android.app.Fragment

fun Activity.addFragment(fragment: Fragment, frameId: Int) {
    fragmentManager.beginTransaction().add(frameId, fragment).commit()
}
fun Activity.replaceFragment(fragment: Fragment, frameId: Int) {
    fragmentManager.beginTransaction().replace(frameId, fragment).commit()
}