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

class FriendsFragment: Fragment(), VkFriendsView{
    override lateinit var presenter: VkFriendsPresenter

    lateinit var friendsAdapter: FriendsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater?.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friendsAdapter = FriendsAdapter(mutableListOf<DomainModels.Friend>()) {
            presenter.onFriendClicked(it)
        }

        with(friendsList) {
            layoutManager = LinearLayoutManager(ctx)
            adapter = friendsAdapter;
        }

        presenter.loadFriends(0, 10)
    }

    override fun showInfo(text: String) {
        showToast(text)
    }

    override fun showFriends(friends: MutableList<DomainModels.Friend>) = with(friendsAdapter){
        this.friends = friends
        notifyDataSetChanged()
    }
}
