package com.example.cleanarchitectureex.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitectureex.R
import com.example.cleanarchitectureex.databinding.ItemLoadProgressBinding

class FooterLoadStateAdapter(
) : LoadStateAdapter<FooterLoadStateAdapter.LoadStateViewHolder>() {
    class LoadStateViewHolder(
        private val binding: ItemLoadProgressBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.pb.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_load_progress, parent, false)
        val binding = ItemLoadProgressBinding.bind(view)
        return LoadStateViewHolder(binding)
    }
}