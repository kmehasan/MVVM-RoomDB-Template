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

data class CourseListOfStudent(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "uid",
        entityColumn = "cid",
        associateBy = Junction(
            value = Mark::class,
            parentColumn = "student_id",
            entityColumn = "course_id"
        )
    )
    val course: List<Course>,
    @Relation(
        parentColumn = "uid",
        entityColumn = "uid",
        associateBy = Junction(
            value = Course::class,
            parentColumn = "cid",
            entityColumn = "teacher_id"
        )
    )
    val teacher: List<User>
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