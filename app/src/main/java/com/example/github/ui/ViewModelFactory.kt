package com.example.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.github.data.Repository
import com.example.github.ui.createuser.CreateUserViewModel
import com.example.github.ui.searchuser.SearchUserViewModel
import com.example.github.ui.uploadimage.UploadImageViewModel
import com.example.github.ui.userdetail.UserDetailViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CreateUserViewModel::class.java) -> CreateUserViewModel(repository) as T
            modelClass.isAssignableFrom(UploadImageViewModel::class.java) -> UploadImageViewModel(repository) as T
            modelClass.isAssignableFrom(SearchUserViewModel::class.java) -> SearchUserViewModel(repository) as T
            modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> UserDetailViewModel(repository) as T
            else -> throw IllegalArgumentException("View Model Not Found")
        }
    }
}