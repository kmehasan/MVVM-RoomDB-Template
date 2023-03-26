package io.github.kmehasan.roomdbtemplate.room

import androidx.room.*

@Dao
interface DAO {
    // user
    @Insert
    fun createUser(user:User)

    @Query("SELECT * FROM user_table WHERE type = 'student'")
    fun getStudentList():List<User>

    @Query("SELECT * FROM user_table  WHERE type = 'teacher'")
    fun getTeacherList():List<User>

    @Query("SELECT * FROM user_table WHERE uid = :id")
    fun getUserById(id:Int):User

    @Query("DELETE FROM user_table WHERE uid = :uid")
    fun deleteUser(uid:Int)

    // course
    @Insert
    fun createCourse(course:Course)

    @Query("SELECT * FROM course_table")
    fun getAllCourses():List<Course>

    @Query("DELETE FROM course_table WHERE cid = :cid")
    fun deleteCourse(cid:Int)

    @Query("SELECT * FROM course_table WHERE teacher_id = :tid")
    fun getCoursesOfTeacher(tid:Int):List<Course>


    // Marks
    @Insert
    fun addCourseToStudent(mark:Mark)

    // Get courses for student
    @Transaction
    @Query("SELECT * FROM user_table WHERE uid = :uid")
    fun getCourseForStudent(uid:Int):CourseListOfStudent


    // Get Students for course
    @Transaction
    @Query("SELECT * FROM course_table WHERE cid = :cid")
    fun getStudentsForCourse(cid:Int):StudentListOfCourse

}