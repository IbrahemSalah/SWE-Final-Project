package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.HiringItem
import kotlinx.coroutines.flow.Flow
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