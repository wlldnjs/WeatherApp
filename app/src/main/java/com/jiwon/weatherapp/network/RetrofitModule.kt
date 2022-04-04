package com.jiwon.weatherapp.network

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @BaseUrl
    @Provides
    @Singleton
    fun provideBaseUrl() = "https://www.metaweather.com"

    @Provides
    @Reusable
    fun provideRetrofit(@BaseUrl baseUrl: String): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // Add Header
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Reusable
    fun provideLocationService(retrofit: Retrofit): LocationService {
        return retrofit.create(LocationService::class.java)
    }
}