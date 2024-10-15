package com.example.SWEFinalProject.data.remote


import retrofit2.Response

interface RemoteDataSource {
    suspend fun signIn(username: String, password: String): Response<String>
    suspend fun signUp(
        username: String,
        password: String,
        phone: String,
        fname: String,
        lname: String
    ): Response<String>
}