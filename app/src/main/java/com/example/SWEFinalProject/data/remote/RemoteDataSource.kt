package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.HiringItem
import retrofit2.Response

interface RemoteDataSource {

    suspend fun fetchHiringList(): Response<List<HiringItem>>
}