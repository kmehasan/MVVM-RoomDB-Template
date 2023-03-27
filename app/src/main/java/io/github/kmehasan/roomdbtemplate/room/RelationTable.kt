package io.github.kmehasan.roomdbtemplate.room

import androidx.room.*

data class TeacherAssociateWithCourse(
    @Embedded
    val course: Course,
    @Relation(
        parentColumn = "teacher_id",
        entityColumn = "uid"
    )
    val teacher:User
)

data class StudentListOfCourse(
    @Embedded
    val course: Course,

    @Relation(
        parentColumn = "cid",
        entityColumn = "uid",
        associateBy = Junction(
            value = Mark::class,
            parentColumn = "course_id",
            entityColumn = "student_id"
        )
    )
    val user: List<User>
)