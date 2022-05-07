package com.example.github.ui.searchuser

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentSearchUserBinding
import com.example.github.di.App
import com.example.github.ui.RecyclerViewAdapter
import com.example.github.ui.ViewModelFactory

class SearchUserFragment : Fragment(R.layout.fragment_search_user),
    RecyclerViewAdapter.ItemClickListener {

    private val searchUserViewModel by viewModels<SearchUserViewModel> { ViewModelFactory((requireActivity().application as App).serviceLocator.repository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchUserBinding.bind(view)

        val recyclerViewAdapter = RecyclerViewAdapter(this)
        binding.recyclerViewFragmentSearchUser.apply {
            adapter = recyclerViewAdapter
        }

        lifecycleScope.launchWhenStarted {
            searchUserViewModel.userList.collect {
                recyclerViewAdapter.submitList(it)
            }
        }

        binding.searchButtonFragmentSearchUser.setOnClickListener {
            val radioButtonId = binding.radioGroupFragmentSearchUser.checkedRadioButtonId
            val key = view.findViewById<RadioButton>(radioButtonId).text.toString()
            val value = binding.searchFragmentSearchUser.text.toString()
            searchUserViewModel.searchUser(
                mapOf(key to value)
            )
        }

    }

    override fun itemClick(id: String) {
        val action = SearchUserFragmentDirections.actionSearchUserFragmentToUserDetailFragment(id)
        findNavController().navigate(action)
    }
}