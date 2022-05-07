package com.example.github.data.remote.api

import com.example.github.data.model.UserListModelItem
import okhttp3.MultipartBody
import retrofit2.http.*

interface RemoteAPI {

    @POST("users")
    suspend fun createUser(
        @Body user: UserListModelItem
    ): String

    @Multipart
    @POST("users/{id}/image")
    suspend fun uploadImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Any

    @GET("users")
    suspend fun searchUser(
        @QueryMap map: Map<String, String>
    ): ArrayList<UserListModelItem>

    @GET("users/{id}")
    suspend fun userDetail(
        @Path("id") id: String
    ): UserListModelItem

}