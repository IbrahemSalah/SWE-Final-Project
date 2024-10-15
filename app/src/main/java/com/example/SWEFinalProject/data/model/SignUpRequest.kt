package com.example.SWEFinalProject.data.model

data class SignUpRequest(
    val username: String,
    val password: String,
    val phone: String,
    val firstName: String,
    val lastName: String
)