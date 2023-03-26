package io.github.kmehasan.roomdbtemplate.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import io.github.kmehasan.roomdbtemplate.R
import io.github.kmehasan.roomdbtemplate.databinding.FragmentCourseListBinding
import io.github.kmehasan.roomdbtemplate.databinding.FragmentCreateUserBinding
import io.github.kmehasan.roomdbtemplate.view_model.MainViewModel
import kotlinx.coroutines.launch

class CourseListFragment : Fragment() {

    lateinit var binding: FragmentCourseListBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourseListBinding.inflate(inflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            Navigation.findNavController(binding.fab).navigate(R.id.action_courseListFragment_to_createCourseFragment)
        }
        lifecycleScope.launch { // coroutine on Main
            mainViewModel.getCoursesOfTeacher(3).observe(viewLifecycleOwner){
                Log.d("TAG", "getCoursesOfTeacher: $it")
            }
        }
    }

}