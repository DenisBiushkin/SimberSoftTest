package com.example.simbersofttest.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simbersofttest.data.source.dao.TaskDao
import com.example.simbersofttest.data.source.entity.TaskEntity

@Database(entities = [ TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}