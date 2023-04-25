package com.ayberk.valorantapp.Module

import com.ayberk.valorantapp.Retrofit.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    var baseURL = "https://valorant-api.com/"

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit): RetrofitInstance {
        return retrofit.create(RetrofitInstance::class.java)
    }

    @Singleton
    @Provides
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}