package com.fuh.testapiappsecond.utils

import android.app.Activity
import android.app.Fragment

fun Activity.addFragmentToActivity(fragment: Fragment, frameId: Int) {
    fragmentManager.beginTransaction().add(frameId, fragment).commit()
}
