package io.github.kmehasan.roomdbtemplate.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid:Int = 0,
    val name:String,
    val age:Int,
    val type:String
):Parcelable

@Entity(tableName = "course_table", foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["uid"],
    childColumns = ["teacher_id"],
    onDelete = ForeignKey.CASCADE)])
@Parcelize
data class Course(
    @PrimaryKey(autoGenerate = true)
    val cid:Int = 0,
    val c_name:String,
    val credit:Float,
    val teacher_id:Int
):Parcelable

class Grade{
    companion object {
        fun getGrade(mark: Double): Double = when {
            mark > 80 -> 4.0
            mark > 75 -> 3.75
            mark > 70 -> 3.5
            mark > 65 -> 3.25
            mark > 60 -> 3.0
            mark > 55 -> 2.75
            mark > 50 -> 2.5
            mark > 45 -> 2.25
            mark > 40 -> 2.0
            else -> 0.0
        }
    }
}


@Entity(
    primaryKeys = ["student_id","course_id"],
    tableName = "mark_table", foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["uid"],
    childColumns = ["student_id"],
    onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = Course::class,
        parentColumns = ["cid"],
        childColumns = ["course_id"],
        onDelete = ForeignKey.CASCADE),
])
data class Mark(
    val student_id:Int,
    val course_id:Int,
    val mark:Double = 0.0,
    var gpa:Double = 0.0
){
    init {
        gpa = Grade.getGrade(mark)
    }
}



