package io.github.kmehasan.roomdbtemplate.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import io.github.kmehasan.roomdbtemplate.R
import io.github.kmehasan.roomdbtemplate.databinding.FragmentUserListBinding
import io.github.kmehasan.roomdbtemplate.room.User
import io.github.kmehasan.roomdbtemplate.view.adapter.OnClick
import io.github.kmehasan.roomdbtemplate.view.adapter.UserAdapter
import io.github.kmehasan.roomdbtemplate.view_model.MainViewModel

class UserListFragment : Fragment(), OnClick {

    private var isStudent: Boolean = false
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: FragmentUserListBinding

    var userList = mutableListOf<User>()
    lateinit var adapter:UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(inflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        adapter = UserAdapter(userList,this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isStudent = requireArguments().getBoolean("isStudent")
        binding.rv.adapter = adapter
        binding.fab.setOnClickListener {
            Navigation.findNavController(binding.fab).navigate(R.id.action_userListFragment_to_createUserFragment,arguments)
        }
        if(isStudent)mainViewModel.getAllStudentList().observe(viewLifecycleOwner) {
            userList.clear()
            userList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        else mainViewModel.getAllTeacherList().observe(viewLifecycleOwner) {
            userList.clear()
            userList.addAll(it)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onClick(user: User) {
        val bundle = Bundle()
        bundle.putParcelable("user",user)
        Navigation.findNavController(binding.root).navigate(R.id.action_userListFragment_to_userDetailsFragment,bundle)
    }

    override fun onLongClick(user: User) {
        mainViewModel.deleteUser(user.uid)
        userList.remove(user)
        adapter.notifyDataSetChanged()
    }

}