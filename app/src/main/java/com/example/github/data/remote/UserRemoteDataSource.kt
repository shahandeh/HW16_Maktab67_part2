package com.example.github.data.remote

import com.example.github.data.IRemoteDataSource
import com.example.github.data.model.UserListModelItem
import com.example.github.data.remote.api.RemoteAPI
import okhttp3.MultipartBody

class UserRemoteDataSource(
    private val remoteAPI: RemoteAPI
): IRemoteDataSource {
    override suspend fun createUser(user: UserListModelItem): String {
        return remoteAPI.createUser(user)
    }

    override suspend fun uploadImage(id: String, image: MultipartBody.Part) {
        remoteAPI.uploadImage(id, image)
    }

    override suspend fun searchUser(map: Map<String, String>): ArrayList<UserListModelItem> {
        return remoteAPI.searchUser(map)
    }

    override suspend fun userDetail(id: String): UserListModelItem {
        return remoteAPI.userDetail(id)
    }
}