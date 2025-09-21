package com.example.comics.di

import com.example.comics.data.network.MovieProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {
    @Provides
    fun providesMovieProvider() = MovieProvider

}