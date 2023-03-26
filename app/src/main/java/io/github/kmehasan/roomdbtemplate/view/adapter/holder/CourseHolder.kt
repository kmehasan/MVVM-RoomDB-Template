package io.github.kmehasan.roomdbtemplate.view.adapter.holder

import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.kmehasan.roomdbtemplate.databinding.UserRowViewHolderBinding
import io.github.kmehasan.roomdbtemplate.room.Course
import io.github.kmehasan.roomdbtemplate.view.adapter.CourseOnClick

class CourseHolder(val view: UserRowViewHolderBinding) :RecyclerView.ViewHolder(view.root) {
    fun bind(course_with_teacher: Course, callback: CourseOnClick){
        val course = course_with_teacher
        view.id.text = course.cid.toString()
        view.name.text = course.c_name
//        view.age.text = course_with_teacher.teacher.name
        view.card.setOnClickListener { callback.onClick(course) }
        view.card.setOnLongClickListener {
            callback.onLongClick(course)
            return@setOnLongClickListener true
        }

    }
    fun decoreHeader(v:TextView){
        v.setTypeface(v.typeface, Typeface.BOLD)
    }
    fun bindHeader(){
        view.id.text = "ID"
        view.name.text = "Name"
        view.age.text = "Teacher"
        view.card.setBackgroundColor(Color.GRAY)
        decoreHeader(view.id)
        decoreHeader(view.name)
        decoreHeader(view.age)
    }
}