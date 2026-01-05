package com.example.listadapter.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.listadapter.databinding.ItemCatBinding
import com.example.listadapter.model.Cat

class CatAdapter : ListAdapter<Cat, CatAdapter.CatViewHolder>(
    diffUtilCallback
) {
    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                // 고유 값 비교
                return oldItem.catId == newItem.catId
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                // 내용 비교
                return oldItem == newItem
            }

        }
    }

    class CatViewHolder(private val binding: ItemCatBinding) : ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            binding.cat = cat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        Log.e("TEST",getItem(position).toString())
        holder.bind(getItem(position))
    }
}