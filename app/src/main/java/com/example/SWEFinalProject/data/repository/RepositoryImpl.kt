package com.example.SWEFinalProject.data.repository

import com.example.SWEFinalProject.data.remote.RemoteDataSource
import com.example.SWEFinalProject.di.AppDispatchers
import com.example.SWEFinalProject.di.Dispatcher
import com.example.SWEFinalProject.util.APIException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : Repository {


    override fun signIn(username: String, password: String) = flow {

        val result = remoteDataSource.signIn(username, password)

        if (result != null) {
            emit(result)
        } else {
            throw APIException("result.message()", 100)
        }
    }.flowOn(ioDispatcher)

    override fun signUp(username: String, password: String, phone: String, fname: String, lname: String) = flow {

        val result = remoteDataSource.signUp(username, password, phone, lname, fname)

        if ( result!= null) {
            emit(result)
        } else {
            throw APIException("result.message()", 100)
        }
    }.flowOn(ioDispatcher)
}