package com.example.listadapter.ui.databinding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapter.ui.adapter.CatAdapter
import com.example.listadapter.model.Cat


@BindingAdapter("submit")
fun RecyclerView.submit(dataList: List<Cat> = emptyList()) {
    Log.e("TEST","submit()")
    if (adapter == null) {
        val catAdapter = CatAdapter()
        adapter = catAdapter
    }
    adapter?.let { catAdapter ->
        (catAdapter as CatAdapter).submitList(dataList.toMutableList())
    }
}