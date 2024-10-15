package com.example.SWEFinalProject.data.remote

import com.example.SWEFinalProject.data.model.SignInRequest
import com.example.SWEFinalProject.data.model.SignUpRequest
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val carApiService: CarApiService) :
    RemoteDataSource {

    override suspend fun signIn(username: String, password: String): String =
        carApiService.signIn(
            SignInRequest(
                username = username,
                password = password,
            )
        )


    override suspend fun signUp(
        username: String,
        password: String,
        phone: String,
        fname: String,
        lname: String
    ): String =
        carApiService.signUp(
            SignUpRequest(
                username = username,
                password = password,
                phone = phone,
                firstName = fname,
                lastName = lname,
            )
        )

}