package com.example.github.ui.searchuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.Repository
import com.example.github.data.model.UserListModelItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchUserViewModel(
    private val repository: Repository
): ViewModel(){

    private val _userList = MutableStateFlow<List<UserListModelItem>>(emptyList())
    val userList = _userList.asStateFlow()

    fun searchUser(map: Map<String, String>){
        viewModelScope.launch {
            val temp = repository.searchUser(map)
            _userList.emit(temp)
        }
    }

}