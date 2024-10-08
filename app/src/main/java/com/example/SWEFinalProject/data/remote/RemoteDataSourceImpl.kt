package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.HiringItem
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val remoteAPI: HiringAPI) : RemoteDataSource {
    override suspend fun fetchHiringList(): Response<List<HiringItem>> =
        remoteAPI.fetchHiringList()
}