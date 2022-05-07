package com.example.github.ui.uploadimage

import androidx.lifecycle.ViewModel
import com.example.github.data.Repository
import okhttp3.MediaType
import okhttp3.MultipartBody


class UploadImageViewModel(
    private val repository: Repository
): ViewModel() {

    suspend fun uploadImage(id:String, image:ByteArray){
        val create = MultipartBody.create(MediaType.parse("image/*"),image)
        val body = MultipartBody.Part.createFormData("image","image.jpg", create)
        repository.uploadImage(id, body)
    }

}