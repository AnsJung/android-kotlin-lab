package com.example.cleanarchitectureex.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cleanarchitectureex.databinding.ItemBinding
import com.example.cleanarchitectureex.domain.model.Item

class MainAdapter : PagingDataAdapter<Item, MainAdapter.MainViewHolder>(DIFF_UTIL) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem

            }
        }
    }

    class MainViewHolder(private val binding: ItemBinding) : ViewHolder(binding.root) {
        fun bind(item: Item, position: Int) {
            binding.item = item.copy(name = "$position - ${item.name}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder((binding))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }
}