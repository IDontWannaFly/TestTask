package com.example.testtask.api

import com.example.testtask.api.dto.ProfileDto
import com.example.testtask.db.AppDatabaseProvider
import com.example.testtask.db.entities.ProfileEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull

class ProfileApi {

    private val profileDao = AppDatabaseProvider.getInstance().getProfileDao()

    suspend fun getProfile() : ProfileDto {

        delay(3000L) //emit long time request

        val dbProfile = profileDao.getProfileFlow().firstOrNull()?.asDto()

        return dbProfile ?: ProfileDto(1, "Михаил", "Зубенко", "01.01.1988", "Мафиозник")
    }

    suspend fun updateProfile(profile: ProfileDto) {

        delay(3000L) //edit long time request

        val dbProfile = ProfileEntity(
            profile.name,
            profile.surname,
            profile.birthDate,
            profile.description
        ).apply { id = profile.id }

        profileDao.insertProfile(dbProfile)
    }

}