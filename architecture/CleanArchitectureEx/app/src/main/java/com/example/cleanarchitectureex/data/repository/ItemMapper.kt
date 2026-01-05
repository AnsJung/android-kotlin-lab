package com.example.cleanarchitectureex.data.repository

import com.example.cleanarchitectureex.data.model.ItemDTO
import com.example.cleanarchitectureex.domain.model.Item

object ItemMapper {

    fun ItemDTO.toItem() = Item(
        id = id ?: -1,
        name = name,
    )

}