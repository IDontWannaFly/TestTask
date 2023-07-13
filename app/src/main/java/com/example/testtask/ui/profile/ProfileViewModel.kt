package com.example.testtask.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.repositories.ProfileRemoteRepository
import com.example.testtask.repositories.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val profileRepo = ProfileRepository()
    private val profileRemoteRepo = ProfileRemoteRepository()

    val profile = profileRepo.getProfile()

    init {
        initProfile()
    }

    private fun initProfile() = viewModelScope.launch {
        val profile = profileRemoteRepo.getProfile()
        profileRepo.updateProfile(
            profile.name,
            profile.surname,
            profile.birthDate,
            profile.description
        )
    }

}