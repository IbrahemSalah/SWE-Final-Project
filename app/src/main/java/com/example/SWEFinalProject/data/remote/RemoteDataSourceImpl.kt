package com.example.SWEFinalProject.data.remote

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val remoteAPI: HiringAPI) :
    RemoteDataSource {

    override suspend fun signIn(username: String, password: String): Response<String> =
        remoteAPI.signIn(
            username = username,
            password = password,
        )


    override suspend fun signUp(username: String, password: String): Response<String> =
        remoteAPI.signUp(
            username = username,
            password = password,
        )

}