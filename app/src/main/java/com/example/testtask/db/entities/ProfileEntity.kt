package com.example.testtask.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtask.api.dto.ProfileDto

@Entity
data class ProfileEntity(
    var name: String,
    var surname: String,
    var birthDate: String,
    var description: String
) {
    fun asDto(): ProfileDto {
        return ProfileDto(id, name, surname, birthDate, description)
    }

    @PrimaryKey(autoGenerate = false)
    var id: Long = 1
}