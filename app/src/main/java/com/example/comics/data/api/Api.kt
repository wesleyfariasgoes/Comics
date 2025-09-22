package com.example.comics.data.api

import com.example.comics.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    @GET("trending/movie/day")
    suspend fun getDay(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ) : MovieResponse
}