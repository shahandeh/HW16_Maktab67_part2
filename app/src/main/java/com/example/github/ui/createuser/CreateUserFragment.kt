package com.example.github.ui.createuser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.data.model.UserListModelItem
import com.example.github.databinding.FragmentCreateUserBinding
import com.example.github.di.App
import com.example.github.ui.ViewModelFactory

class CreateUserFragment : Fragment(R.layout.fragment_create_user) {

    private val createUserViewModel by viewModels<CreateUserViewModel> { ViewModelFactory((requireActivity().application as App).serviceLocator.repository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCreateUserBinding.bind(view)

        binding.createUserFragmentCreateUser.setOnClickListener {

            val hobbies = mutableListOf<String>()
            if (binding.codingFragmentCreateUser.isChecked) hobbies.add("Coding")
            if (binding.gamingFragmentCreateUser.isChecked) hobbies.add("Gaming")
            if (binding.movieFragmentCreateUser.isChecked) hobbies.add("Movie")
            if (binding.sportFragmentCreateUser.isChecked) hobbies.add("Sport")

            if (binding.nameFragmentCreateUser.text.toString().isNotBlank() &&
            binding.familyFragmentCreateUser.text.toString().isNotBlank() &&
            binding.nationalCodeFragmentCreateUser.text.toString().isNotBlank())

                createUserViewModel.createUser(
                    UserListModelItem(
                        null,
                        binding.nameFragmentCreateUser.text.toString(),
                        binding.familyFragmentCreateUser.text.toString(),
                        binding.nationalCodeFragmentCreateUser.text.toString(),
                        hobbies
                    )
                )

            else Toast.makeText(requireContext(), "Please Fill All Inputs", Toast.LENGTH_SHORT)
                .show()
        }

        createUserViewModel.charSequence.observe(viewLifecycleOwner){
            val action = CreateUserFragmentDirections.actionCreateUserFragmentToUploadImageFragment(it)
            findNavController().navigate(action)
        }

    }

}