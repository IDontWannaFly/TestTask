package com.example.testtask.db

import android.content.Context
import androidx.room.Room

object AppDatabaseProvider {

    private var _instance: AppDatabase? = null

    fun getInstance(context: Context? = null) : AppDatabase {
        if (_instance == null) initDatabase(context)
        return _instance!!
    }

    private fun initDatabase(context: Context?) {
        if (context == null) throw NullPointerException("Context is null")
        _instance = Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

}