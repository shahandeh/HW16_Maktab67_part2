package com.example.github.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.Repository
import com.example.github.data.model.UserListModelItem
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val repository: Repository
): ViewModel() {

    private val _userDetail = MutableLiveData<UserListModelItem>()
    val userDetail: LiveData<UserListModelItem> = _userDetail

    fun userDetail(id: String){
        viewModelScope.launch {
            _userDetail.postValue(repository.userDetail(id))
        }
    }

}