package io.github.kmehasan.roomdbtemplate.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import io.github.kmehasan.roomdbtemplate.R
import io.github.kmehasan.roomdbtemplate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.students.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isStudent", true)
            Navigation.findNavController(binding.students).navigate(R.id.action_homeFragment_to_userListFragment,bundle)
        }
        binding.teachers.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isStudent", false)
            Navigation.findNavController(binding.teachers).navigate(R.id.action_homeFragment_to_userListFragment,bundle)
        }
        binding.courses.setOnClickListener { Navigation.findNavController(binding.courses).navigate(R.id.action_homeFragment_to_courseListFragment) }

    }

}