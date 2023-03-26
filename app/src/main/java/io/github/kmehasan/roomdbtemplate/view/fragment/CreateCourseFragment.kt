package io.github.kmehasan.roomdbtemplate.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.github.kmehasan.roomdbtemplate.R
import io.github.kmehasan.roomdbtemplate.databinding.FragmentCreateCourseBinding
import io.github.kmehasan.roomdbtemplate.room.Course
import io.github.kmehasan.roomdbtemplate.view_model.MainViewModel
import kotlinx.coroutines.launch

class CreateCourseFragment : Fragment() {
    lateinit var binding: FragmentCreateCourseBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateCourseBinding.inflate(inflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitBtn.setOnClickListener {
            val course = Course(
                c_name = binding.fullNameEt.text.toString(),
                credit = binding.credit.text.toString().toFloat(),
                teacher_id = binding.teacher.text.toString().toInt()
            )
            lifecycleScope.launch { // coroutine on Main
                mainViewModel.createCourse(course)
                requireActivity().onBackPressed()
            }
        }
    }


}