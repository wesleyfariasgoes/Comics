package com.example.comics.di

import com.example.comics.data.api.Api
import com.example.comics.data.network.MovieProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideApiService(
        movieProvider: MovieProvider
    ): Api {
        return movieProvider.createService(Api::class.java)
    }
}