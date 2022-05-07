package com.example.github.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.github.data.model.UserListModelItem

class DiffCallback: DiffUtil.ItemCallback<UserListModelItem>() {
    override fun areItemsTheSame(oldItem: UserListModelItem, newItem: UserListModelItem): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: UserListModelItem, newItem: UserListModelItem ): Boolean {
        return oldItem == newItem
    }
}