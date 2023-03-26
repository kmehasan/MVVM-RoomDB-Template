package io.github.kmehasan.roomdbtemplate.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.github.kmehasan.roomdbtemplate.R
import io.github.kmehasan.roomdbtemplate.databinding.FragmentCreateUserBinding
import io.github.kmehasan.roomdbtemplate.room.User
import io.github.kmehasan.roomdbtemplate.view_model.MainViewModel
import kotlinx.coroutines.launch

class CreateUserFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: FragmentCreateUserBinding
    var isStudent = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateUserBinding.inflate(inflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isStudent = requireArguments().getBoolean("isStudent")
        if(isStudent){
            binding.heading.text = resources.getText(R.string.add_new_student)
        }
        else {
            binding.heading.text = resources.getText(R.string.add_new_teacher)
        }
        binding.submitBtn.setOnClickListener {
            val user = User(
                name = binding.fullNameEt.text.toString(),
                age = binding.age.text.toString().toInt(),
                type = if(isStudent) "student" else "teacher"
            )
            lifecycleScope.launch { // coroutine on Main
                mainViewModel.createUser(user)
                requireActivity().onBackPressed()
            }
        }
    }
}