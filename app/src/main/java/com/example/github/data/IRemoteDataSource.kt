package com.example.github.data

import com.example.github.data.model.UserListModelItem
import okhttp3.MultipartBody

interface IRemoteDataSource {

    suspend fun createUser(user: UserListModelItem): String

    suspend fun uploadImage(id: String, image: MultipartBody.Part)

    suspend fun searchUser(map: Map<String, String>): ArrayList<UserListModelItem>

    suspend fun userDetail(id: String): UserListModelItem

}