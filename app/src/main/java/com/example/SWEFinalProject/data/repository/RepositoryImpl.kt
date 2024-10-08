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


    override fun fetchHiringList() = flow {

        val result = remoteDataSource.fetchHiringList()

        if (result.isSuccessful && result.body() != null) {
            emit(result.body()!!)
        } else {
            throw APIException(result.message(), result.code())
        }
    }.flowOn(ioDispatcher)

}