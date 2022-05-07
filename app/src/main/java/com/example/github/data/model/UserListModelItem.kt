package com.example.github.data.model

data class UserListModelItem(
    val _id: String?,
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String>?
)