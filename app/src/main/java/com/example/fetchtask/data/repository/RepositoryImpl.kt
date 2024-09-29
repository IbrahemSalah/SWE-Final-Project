package com.example.fetchtask.data.repository

import com.example.fetchtask.data.remote.RemoteDataSource
import com.example.fetchtask.di.AppDispatchers
import com.example.fetchtask.di.Dispatcher
import com.example.fetchtask.util.APIException
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