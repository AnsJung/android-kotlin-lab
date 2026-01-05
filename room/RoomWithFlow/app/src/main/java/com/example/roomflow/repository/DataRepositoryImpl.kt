package com.example.roomflow.repository

import com.example.roomflow.database.TextDatabase
import com.example.roomflow.database.entity.TextEntity
import kotlinx.coroutines.flow.Flow

class DataRepositoryImpl(val database: TextDatabase) : DataRepository {
    override fun getAll(): List<TextEntity> {
        return database.textDao().getAll()
    }

    override fun getAllDataFlow(): Flow<List<TextEntity>> {
        return database.textDao().getAllDataFlow()
    }

    override fun insert(textEntity: TextEntity) {
        database.textDao().insert(textEntity)
    }

    override fun deleteAll() {
        database.textDao().deleteAll()
    }
}