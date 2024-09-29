package com.example.fetchtask.data.remote

import com.example.fetchtask.data.model.HiringItem
import retrofit2.Response

interface RemoteDataSource {

    suspend fun fetchHiringList(): Response<List<HiringItem>>
}