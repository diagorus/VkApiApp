package com.fuh.testapiappsecond.view.general

import com.fuh.testapiappsecond.model.DomainModels
import com.fuh.testapiappsecond.presenter.VkFriendsPresenter

interface VkFriendsView: BaseView<VkFriendsPresenter> {
    fun showFriends(friends: MutableList<DomainModels.Friend>)
    fun showInfo(text: String)
    fun showProgress()
    fun hideProgress()
    fun showError(cause: String)
    fun hideError()
}