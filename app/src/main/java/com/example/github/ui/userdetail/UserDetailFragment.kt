package com.example.github.ui.userdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.github.R
import com.example.github.databinding.FragmentUserDetailBinding
import com.example.github.di.App
import com.example.github.ui.ViewModelFactory

class UserDetailFragment: Fragment(R.layout.fragment_user_detail) {

    private val args: UserDetailFragmentArgs by navArgs()

    private val userDetailViewModel by viewModels<UserDetailViewModel> { ViewModelFactory((requireActivity().application as App).serviceLocator.repository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUserDetailBinding.bind(view)

        userDetailViewModel.userDetail(args.id)

        userDetailViewModel.userDetail.observe(viewLifecycleOwner){
            binding.firstNameFragmentUserDetail.text = it.firstName
            binding.lastNameFragmentUserDetail.text = it.lastName
            binding.nationalCodeFragmentUserDetail.text = it.nationalCode
            binding.hobieFragmentUserDetail.text = it.hobbies.toString()
        }

    }

}