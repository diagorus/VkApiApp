package com.fuh.testapiappsecond.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fuh.testapiappsecond.R
import com.fuh.testapiappsecond.model.DomainModels
import com.fuh.testapiappsecond.utils.ctx
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendsAdapter(var friends: MutableList<DomainModels.Friend>,
                     val itemClick: (DomainModels.Friend) -> Unit):
        RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFriend(friends[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(view, itemClick)
    }

    class ViewHolder(itemView: View, val itemClick: (DomainModels.Friend) -> Unit):
            RecyclerView.ViewHolder(itemView) {
        fun bindFriend(friend: DomainModels.Friend) = with(friend) {
            Glide.with(itemView.ctx).load(photoUrl).into(itemView.avatar)
            itemView.name.text = fullName
            itemView.city.text =
                    String.format(itemView.ctx.getString(R.string.item_friend_city), city)
            itemView.domain.text =
                    String.format(itemView.ctx.getString(R.string.item_friend_domain), domain)
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}