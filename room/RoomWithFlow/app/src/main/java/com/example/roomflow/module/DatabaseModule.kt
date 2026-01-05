package com.example.roomflow.module

import android.content.Context
import androidx.room.Room
import com.example.roomflow.database.TextDatabase
import com.example.roomflow.database.dao.TextDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): TextDatabase {
        return Room.databaseBuilder(
            context,
            TextDatabase::class.java,
            "text_db.db"
        ).fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun providesDAO(textDatabase: TextDatabase): TextDAO =
        textDatabase.textDao()
}