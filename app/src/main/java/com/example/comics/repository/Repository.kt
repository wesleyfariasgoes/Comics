package com.example.comics.repository

import android.util.Log
import com.google.common.hash.Hashing
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    suspend fun getMovies(): DataModel {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
            .getDay(
                token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMjJjNmNjZTJmMThjZGQ3YmEwYmUyYjlmYTJiOGU3MyIsIm5iZiI6MTc0MzE3OTQyMi4zODgsInN1YiI6IjY3ZTZjZTllZjg0Njc5NGU5OTEwZjY0MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.i-FWN8afMCXSY6Skjs1K_5pf_qyX8yaU0ehq4ve8SqM"
            ).await()
    }
}
