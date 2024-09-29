package com.example.fetchtask.di

import com.example.fetchtask.BuildConfig
import com.example.fetchtask.data.remote.HiringAPI
import com.example.fetchtask.data.remote.RemoteDataSource
import com.example.fetchtask.data.remote.RemoteDataSourceImpl
import com.example.fetchtask.data.repository.Repository
import com.example.fetchtask.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHiringAPI(httpClient: OkHttpClient): HiringAPI {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BaseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HiringAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(remoteAPI: HiringAPI): RemoteDataSource {
        return RemoteDataSourceImpl(remoteAPI)
    }

    @Provides
    @Singleton
    fun provideRepository(
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(remoteDataSource, ioDispatcher)
    }
}