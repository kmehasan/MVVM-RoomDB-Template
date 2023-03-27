package io.github.kmehasan.roomdbtemplate.repository

import android.content.Context
import io.github.kmehasan.roomdbtemplate.room.*

class Repository(context:Context) {
    var dao = RoomDB.getInstance(context).getDAO()
    // Create
    fun createUser(user: User) = dao.createUser(user)
    fun createCourse(course: Course) = dao.createCourse(course)
    fun addCourseToStudent(mark: Mark) = dao.addCourseToStudent(mark)

    // Select
    fun getStudent():List<User> = dao.getStudentList()
    fun getTeacherList():List<User> = dao.getTeacherList()
    fun getAllCourses():List<TeacherAssociateWithCourse> = dao.getAllCourses()
    fun getCoursesOfTeacher(uid:Int):List<Course> = dao.getCoursesOfTeacher(uid)

    // Delete
    fun deleteUser(uid:Int) = dao.deleteUser(uid)

    // Get Course list For Student
    fun getCourseForStudent(uid:Int):List<TeacherAssociateWithCourse> = dao.getCourseForStudent(uid)

    // Get Students for course
    fun getStudentsForCourse(cid:Int):StudentListOfCourse = dao.getStudentsForCourse(cid)

}