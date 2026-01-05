package com.example.roomflow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomflow.database.dao.TextDAO
import com.example.roomflow.database.entity.TextEntity

@Database(entities = [TextEntity::class], version = 2)
abstract class TextDatabase :RoomDatabase() {

    abstract fun textDao(): TextDAO
}