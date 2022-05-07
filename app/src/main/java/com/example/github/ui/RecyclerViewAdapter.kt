package com.example.github.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.model.UserListModelItem
import com.example.github.databinding.RecyclerViewSampleBinding

class RecyclerViewAdapter(
    private val itemClickListener: ItemClickListener
): ListAdapter<UserListModelItem, RecyclerViewAdapter.RecyclerViewHolder>(DiffCallback()) {

    inner class RecyclerViewHolder(private val binding: RecyclerViewSampleBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            itemClickListener.itemClick(getItem(adapterPosition)._id.toString())
        }

        fun bind(user: UserListModelItem){
            binding.textViewRecyclerViewSample.text = user.firstName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(RecyclerViewSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface ItemClickListener{
        fun itemClick(id: String)
    }

}