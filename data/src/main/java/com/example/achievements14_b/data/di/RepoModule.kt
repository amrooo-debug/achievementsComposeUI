package com.example.achievements14_b.data.di

import com.example.achievements14_b.data.api.ProjectApi
import com.example.achievements14_b.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepo(projectApi: ProjectApi): Repository {
        return Repository(projectApi)
    }

}