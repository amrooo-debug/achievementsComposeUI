package com.example.achievements14_b.data.di

import com.example.achievements14_b.data.api.ProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkRepository {

    private val baseUrl = "https://786b905e-735c-4be6-adfb-949d5dadee32.mock.pstmn.io/"


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()

            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ProjectApi {
        return retrofit.create(ProjectApi::class.java)
    }

}