package com.example.roomflow.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomflow.database.entity.TextEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TextDAO {

    @Query("SELECT * FROM TEXT")
    fun getAll(): List<TextEntity>

    @Query("SELECT * FROM TEXT")
    fun getAllDataFlow(): Flow<List<TextEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(textEntity: TextEntity)

    @Query("DELETE FROM TEXT")
    fun deleteAll()
}