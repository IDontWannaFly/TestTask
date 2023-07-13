package com.example.testtask.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.db.entities.ProfileEntity
import com.example.testtask.repositories.ProfileRemoteRepository
import com.example.testtask.repositories.ProfileRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {

    private val repository = ProfileRepository()
    private val remoteRepository = ProfileRemoteRepository()

    private var profile: ProfileEntity? = null

    val stateFlow = MutableStateFlow<State>(State.UpdateName)

    init {
        initProfile()
    }

    private fun initProfile() = viewModelScope.launch {
        profile = repository.getProfile().firstOrNull() ?: ProfileEntity("", "", "", "")
    }

    suspend fun updateProfile() {
        val profile = this.profile!!
        remoteRepository.updateProfile(
            profile.id,
            profile.name,
            profile.surname,
            profile.birthDate,
            profile.description
        )
        stateFlow.emit(State.ProfileUpdated)
    }

    suspend fun updateName(name: String, surname: String) {
        profile?.name = name
        profile?.surname = surname
        stateFlow.emit(State.UpdateBirthDate)
    }

    suspend fun updateBirthDate(date: String) {
        profile?.birthDate = date
        stateFlow.emit(State.UpdateDescription)
    }

    suspend fun updateDescription(description: String) {
        profile?.description = description
    }

    sealed class State {
        object UpdateName : State()
        object UpdateBirthDate : State()
        object UpdateDescription : State()
        object ProfileUpdated : State()
    }

}