package io.github.kmehasan.roomdbtemplate.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kmehasan.roomdbtemplate.databinding.UserRowViewHolderBinding
import io.github.kmehasan.roomdbtemplate.room.User
import io.github.kmehasan.roomdbtemplate.view.adapter.holder.UserHolder

class UserAdapter(private val list : List<User>, val callback:OnClick):RecyclerView.Adapter<UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = UserRowViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        if(position == 0) holder.bindHeader()
        else holder.bind(list[position-1],callback)
    }
}
interface OnClick{
    fun onClick(user:User)
    fun onLongClick(user: User)
}