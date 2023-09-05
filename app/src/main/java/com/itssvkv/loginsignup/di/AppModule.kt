package com.itssvkv.loginsignup.di

import android.os.Bundle
import android.util.Log
import com.itssvkv.loginsignup.data.network.ApiCalls
import com.itssvkv.loginsignup.utils.common.Common.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideRetrofit(okHttpClient:OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiCalls{
        return retrofit.create(ApiCalls::class.java)
    }

    @Provides
    @Singleton
    fun provideBundle() : Bundle{
        return Bundle()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val httpClientLoggingInterceptor= HttpLoggingInterceptor{ msg ->
            Log.i("logInterceptor", "Interceptor : $msg")
        }
        httpClientLoggingInterceptor.level= HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder().apply {
            addInterceptor(httpClientLoggingInterceptor)
        }.build()
    }
}