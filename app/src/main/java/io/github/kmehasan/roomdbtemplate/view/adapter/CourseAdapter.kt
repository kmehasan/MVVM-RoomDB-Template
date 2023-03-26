package io.github.kmehasan.roomdbtemplate.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kmehasan.roomdbtemplate.databinding.UserRowViewHolderBinding
import io.github.kmehasan.roomdbtemplate.room.Course
import io.github.kmehasan.roomdbtemplate.room.TeacherAssociateWithCourse
import io.github.kmehasan.roomdbtemplate.view.adapter.holder.CourseHolder

class CourseAdapter(private val list : List<TeacherAssociateWithCourse>, val callback:CourseOnClick):RecyclerView.Adapter<CourseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view = UserRowViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CourseHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        if(position == 0) holder.bindHeader()
        else holder.bind(list[position-1],callback)
    }
}

interface CourseOnClick{
    fun onClick(course: Course)
    fun onLongClick(course: Course)
}