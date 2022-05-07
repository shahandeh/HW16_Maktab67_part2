package com.example.github.ui.createuser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.Repository
import com.example.github.data.model.UserListModelItem
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val repository: Repository
): ViewModel() {

    val charSequence = MutableLiveData<String>()

    fun createUser(user: UserListModelItem){
        viewModelScope.launch { charSequence.postValue(repository.createUser(user)) }
    }

}