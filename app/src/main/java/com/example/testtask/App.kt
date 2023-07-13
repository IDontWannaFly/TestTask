package com.example.testtask

import android.app.Application
import com.example.testtask.db.AppDatabaseProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabaseProvider.getInstance(this)
    }

}