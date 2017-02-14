package com.fuh.testapiappsecond.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuh.testapiappsecond.R
import com.fuh.testapiappsecond.adapter.FriendsAdapter
import com.fuh.testapiappsecond.model.DomainModels
import com.fuh.testapiappsecond.presenter.VkFriendsPresenter
import com.fuh.testapiappsecond.utils.ctx
import com.fuh.testapiappsecond.utils.showToast
import com.fuh.testapiappsecond.view.general.VkFriendsView
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.layout_connection_error.*
import kotlinx.android.synthetic.main.layout_progress_frame.*

class FriendsFragment: Fragment(), VkFriendsView {

    override lateinit var presenter: VkFriendsPresenter

    lateinit var friendsAdapter: FriendsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater?.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        presenter.loadFriends(0, 10)
    }

    fun init() {
        friendsAdapter = FriendsAdapter(mutableListOf<DomainModels.Friend>()) {
            presenter.onFriendClicked(it)
        }

        with(friendsList) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = friendsAdapter;
        }

        errorReload.setOnClickListener { presenter.loadFriends(0, 10) }
    }

    override fun showInfo(text: String) {
        showToast(text)
    }

    override fun showFriends(friends: MutableList<DomainModels.Friend>) = with(friendsAdapter){
        this.friends = friends
        notifyDataSetChanged()
    }

    override fun showProgress() {
        progressLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressLayout.visibility = View.GONE
    }

    override fun showError(cause: String) {
        errorInfo.text = cause
        errorLayout.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorLayout.visibility = View.GONE
    }
}
