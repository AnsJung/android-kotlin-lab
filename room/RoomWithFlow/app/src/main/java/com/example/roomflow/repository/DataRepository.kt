package com.example.roomflow.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomflow.database.entity.TextEntity
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getAll() : List<TextEntity>

    fun getAllDataFlow(): Flow<List<TextEntity>>

    fun insert(textEntity: TextEntity)

    fun deleteAll()
}