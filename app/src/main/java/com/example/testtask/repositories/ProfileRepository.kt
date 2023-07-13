package com.example.testtask.repositories

import com.example.testtask.api.ProfileApi
import com.example.testtask.db.AppDatabaseProvider
import com.example.testtask.db.entities.ProfileEntity
import kotlinx.coroutines.flow.Flow

class ProfileRepository {

    private val profileDao = AppDatabaseProvider.getInstance().getProfileDao()

    suspend fun updateProfile(name: String, surname: String, birthDate: String, description: String) {
        profileDao.insertProfile(ProfileEntity(name, surname, birthDate, description))
    }

    fun getProfile() : Flow<ProfileEntity> {
        return profileDao.getProfileFlow()
    }

}