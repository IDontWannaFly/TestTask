package com.example.testtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtask.db.dao.ProfileDao
import com.example.testtask.db.entities.ProfileEntity

@Database(
    entities = [
        ProfileEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProfileDao() : ProfileDao

}