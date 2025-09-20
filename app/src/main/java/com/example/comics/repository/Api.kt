package com.example.comics.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    @GET("trending/movie/day")
    fun getDay(
        @Header("Authorization") token: String
    ) : Call<DataModel>
}