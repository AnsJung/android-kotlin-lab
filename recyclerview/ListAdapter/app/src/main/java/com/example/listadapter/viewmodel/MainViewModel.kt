package com.example.listadapter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadapter.model.Cat

class MainViewModel : ViewModel() {
    private val list = mutableListOf<Cat>()

    private val _itemList = MutableLiveData<List<Cat>>()
    val itemList: LiveData<List<Cat>> = _itemList

    private var _dataList = MutableLiveData(emptyList<Cat>())
    val dataList: LiveData<List<Cat>> = _dataList

    private var _text = MutableLiveData<Int>(1111)
    val text: LiveData<Int> = _text

    init {
        _itemList.value = list
//        _dataList.value =
//            listOf(
//                Cat(1, "Cat1", 1),
//                Cat(2, "Cat2", 2),
//                Cat(3, "Cat3", 3),
//                Cat(4, "Cat4", 4),
//                Cat(5, "Cat5", 5),
//            )
        list.add(Cat(1, "Cat1", 1))
        list.add(Cat(2, "Cat2", 20))
        list.add(Cat(3, "Cat3", 30))
        list.add(Cat(4, "Cat4", 40))
        list.add(Cat(5, "Cat5", 50))
        _itemList.value = list

    }

    fun changeList() {
        Log.e("TEST", "CHANGE LIST")
        list.removeAt(0)
        list.removeAt(0)
        list.add(Cat(6, "Cat6", 60))
        list.add(Cat(7, "Cat7", 70))
        _itemList.value = list
        _text.value = 2222
//        _dataList.value =
//            listOf(
//                Cat(3, "Cat3", 3),
//                Cat(4, "Cat4", 4),
//                Cat(5, "Cat5", 5),
//                Cat(6, "Cat6", 60),
//                Cat(7, "Cat7", 70),
//                Cat(8, "Cat8", 80),
//            )
    }
}