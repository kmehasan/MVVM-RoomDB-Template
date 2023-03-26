package io.github.kmehasan.roomdbtemplate.view.adapter.holder

import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.kmehasan.roomdbtemplate.databinding.UserRowViewHolderBinding
import io.github.kmehasan.roomdbtemplate.room.User
import io.github.kmehasan.roomdbtemplate.view.adapter.OnClick

class UserHolder(val view: UserRowViewHolderBinding) :RecyclerView.ViewHolder(view.root) {
    fun bind(user: User, callback:OnClick){
        view.id.text = user.uid.toString()
        view.name.text = user.name
        view.age.text = user.age.toString()
        view.card.setOnClickListener { callback.onClick(user) }
        view.card.setOnLongClickListener {
            callback.onLongClick(user)
            return@setOnLongClickListener true
        }

    }
    fun decoreHeader(v:TextView){
        v.setTypeface(v.typeface, Typeface.BOLD)
    }
    fun bindHeader(){
        view.id.text = "ID"
        view.name.text = "Name"
        view.age.text = "Age"
        view.card.setBackgroundColor(Color.GRAY)
        decoreHeader(view.id)
        decoreHeader(view.name)
        decoreHeader(view.age)
    }
}