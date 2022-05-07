package com.example.github.di

import com.example.github.data.IRemoteDataSource
import com.example.github.data.Repository
import com.example.github.data.remote.UserRemoteDataSource
import com.example.github.data.remote.api.NetworkManager

class ServiceLocator() {

    private val userRemoteDataSource: IRemoteDataSource = UserRemoteDataSource(NetworkManager.service)

    val repository = Repository(userRemoteDataSource)

}