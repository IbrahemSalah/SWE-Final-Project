package com.example.SWEFinalProject.di

import com.example.SWEFinalProject.BuildConfig
import com.example.SWEFinalProject.data.remote.CarApiService
import com.example.SWEFinalProject.data.remote.RemoteDataSource
import com.example.SWEFinalProject.data.remote.RemoteDataSourceImpl
import com.example.SWEFinalProject.data.repository.Repository
import com.example.SWEFinalProject.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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
    fun provideCarAPI(httpClient: OkHttpClient): CarApiService {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BaseUrl)
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())  // Add this to handle String responses
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(remoteDataSource, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        carApiService: CarApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(carApiService)
    }
}