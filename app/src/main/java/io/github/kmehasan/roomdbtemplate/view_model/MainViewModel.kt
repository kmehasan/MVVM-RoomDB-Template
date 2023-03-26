package io.github.kmehasan.roomdbtemplate.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.kmehasan.roomdbtemplate.repository.Repository
import io.github.kmehasan.roomdbtemplate.room.*
import kotlinx.coroutines.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var repository = Repository(application.baseContext)

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Poor Connection")
        Log.e("TAG", "Error : ${throwable.localizedMessage}")
    }
    //for coroutine Exception
    val errorMessage = MutableLiveData<String>()
    private fun onError(message: String) {
        errorMessage.postValue(message)
        Log.e("TAG", "onError: $message")
    }
    fun createUser(user: User){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            repository.createUser(user)
        }

    }
    fun createCourse(course: Course){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            repository.createCourse(course)
        }

    }
    fun addCourseToStudent(mark: Mark){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            repository.addCourseToStudent(mark)
        }

    }
    fun getAllStudentList():MutableLiveData<List<User>>{
        val studentList:MutableLiveData<List<User>> = MutableLiveData()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            studentList.postValue(repository.getStudent())
        }
        return studentList;
    }

    fun getAllTeacherList():MutableLiveData<List<User>>{
        val teacherList:MutableLiveData<List<User>> = MutableLiveData()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            teacherList.postValue(repository.getTeacherList())
        }
        return teacherList;
    }
    fun getAllCourses():MutableLiveData<List<Course>>{
        val teacherList:MutableLiveData<List<Course>> = MutableLiveData()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            teacherList.postValue(repository.getAllCourses())
        }
        return teacherList;
    }
    fun getCoursesOfTeacher(uid:Int):MutableLiveData<List<Course>>{
        val teacherList:MutableLiveData<List<Course>> = MutableLiveData()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            teacherList.postValue(repository.getCoursesOfTeacher(uid))
        }
        return teacherList
    }
    fun getCoursesOfStudent(uid:Int):MutableLiveData<CourseListOfStudent>{
        val couresOfStudent:MutableLiveData<CourseListOfStudent> = MutableLiveData()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            couresOfStudent.postValue(repository.getCourseForStudent(uid))
        }
        return couresOfStudent
    }

    fun getStudentsForCourse(cid:Int):MutableLiveData<StudentListOfCourse>{
        val couresOfStudent:MutableLiveData<StudentListOfCourse> = MutableLiveData()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            couresOfStudent.postValue(repository.getStudentsForCourse(cid))
        }
        return couresOfStudent
    }

    fun deleteUser(uid:Int){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            repository.deleteUser(uid)
        }
    }
}
