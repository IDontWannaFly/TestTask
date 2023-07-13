package com.example.testtask.repositories

import com.example.testtask.api.ProfileApi
import com.example.testtask.api.dto.ProfileDto

class ProfileRemoteRepository {

    private val profileApi = ProfileApi()

    suspend fun updateProfile(id: Long, name: String, surname: String, birthDate: String, description: String) {
        profileApi.updateProfile(ProfileDto(id, name, surname, birthDate, description))
    }

    suspend fun getProfile() : ProfileDto {
        return profileApi.getProfile()
    }

}