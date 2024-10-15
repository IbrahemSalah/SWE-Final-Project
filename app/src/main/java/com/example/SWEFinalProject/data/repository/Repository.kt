package com.example.SWEFinalProject.data.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun signIn(username: String, password: String): Flow<String>
    fun signUp(
        username: String,
        password: String,
        phone: String,
        fname: String,
        lname: String
    ): Flow<String>
}