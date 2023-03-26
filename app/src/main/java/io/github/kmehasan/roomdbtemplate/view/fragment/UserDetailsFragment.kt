package io.github.kmehasan.roomdbtemplate.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.github.kmehasan.roomdbtemplate.R
import io.github.kmehasan.roomdbtemplate.databinding.FragmentUserDetailsBinding
import io.github.kmehasan.roomdbtemplate.room.Course
import io.github.kmehasan.roomdbtemplate.room.Mark
import io.github.kmehasan.roomdbtemplate.room.User
import io.github.kmehasan.roomdbtemplate.view.adapter.CourseAdapter
import io.github.kmehasan.roomdbtemplate.view.adapter.CourseOnClick
import io.github.kmehasan.roomdbtemplate.view_model.MainViewModel

class UserDetailsFragment : Fragment(), CourseOnClick {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding:FragmentUserDetailsBinding
    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater)
        user = requireArguments().getParcelable("user")!!
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(user){
            binding.name.text = name
            binding.age.text = "Age : %d".format(age)
            binding.id.text = "ID : %d".format(uid)
        }
        binding.fab.setOnClickListener {
            showBottomSheet()
        }
        mainViewModel.getCoursesOfStudent(user.uid).observe(viewLifecycleOwner){
//            binding.rv.adapter = CourseAdapter(it,this)
            Log.d("TAG", "getCoursesOfStudent: "+it)
        }

    }
    fun showBottomSheet(){
        mainViewModel.getAllCourses().observe(viewLifecycleOwner){
            var selectedCourse:Course? = null
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, it.map { course -> return@map course.course.c_name })

            val bottomSheet = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.add_course_to_user_bottom_sheet)
            val course = bottomSheet.findViewById<AutoCompleteTextView>(R.id.courseSelector)
            course?.setAdapter(adapter)
            course?.setOnItemClickListener { adapterView, view, i, l ->
                selectedCourse = it[i].course
            }
            bottomSheet.findViewById<Button>(R.id.add)?.setOnClickListener {
                if(selectedCourse!=null){
                    val mark = Mark(
                        student_id = user.uid,
                        course_id = selectedCourse!!.cid
                    )
                    mainViewModel.addCourseToStudent(mark)
                }
            }
            bottomSheet.show()
        }

    }

    override fun onClick(course: Course) {

    }

    override fun onLongClick(course: Course) {

    }
}