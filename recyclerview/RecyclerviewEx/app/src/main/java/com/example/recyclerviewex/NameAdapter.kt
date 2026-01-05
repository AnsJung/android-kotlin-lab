package com.example.recyclerviewex

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewex.databinding.ItemNameBinding

class NameAdapter(private val itemWidth: Int) : ListAdapter<NameItem, NameAdapter.NameViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NameAdapter.NameViewHolder {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameAdapter.NameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NameViewHolder(private val binding: ItemNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cList = listOf(Color.BLUE, Color.RED, Color.MAGENTA)
        fun bind(name: NameItem) {
            binding.tvName.text = name.name
            binding.tvName.setTextColor(Color.WHITE)
            binding.root.setBackgroundColor(cList[position % cList.size])
            val layoutParams = binding.root.layoutParams
            layoutParams.width = itemWidth
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<NameItem>() {
            override fun areItemsTheSame(
                oldItem: NameItem,
                newItem: NameItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: NameItem,
                newItem: NameItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}