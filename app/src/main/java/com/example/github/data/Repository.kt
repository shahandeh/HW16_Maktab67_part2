package com.example.github.data

import com.example.github.data.model.UserListModelItem
import okhttp3.MultipartBody

class Repository(
    private val remoteDataSource: IRemoteDataSource
) {

    suspend fun createUser(user: UserListModelItem): String {
        return remoteDataSource.createUser(user)
    }

    suspend fun uploadImage(id: String, image: MultipartBody.Part){
        remoteDataSource.uploadImage(id, image)
    }

    suspend fun searchUser(map: Map<String, String>): ArrayList<UserListModelItem> {
        return remoteDataSource.searchUser(map)
    }

    suspend fun userDetail(id: String): UserListModelItem{
        return remoteDataSource.userDetail(id)
    }

}