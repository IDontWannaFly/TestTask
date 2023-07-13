package com.example.testtask.api.dto

data class ProfileDto(
    val id: Long,
    val name: String,
    val surname: String,
    val birthDate: String,
    val description: String
)