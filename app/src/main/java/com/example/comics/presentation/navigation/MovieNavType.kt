package com.example.comics.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.comics.domain.model.Movie
import com.google.gson.Gson
import java.io.Serializable

val movieNavType = object : NavType<Movie>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Movie? {
        return bundle.getSerializable(key) as Movie?
    }

    override fun parseValue(value: String): Movie {
        return Gson().fromJson(Uri.decode(value), Movie::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putSerializable(key, value as Serializable)
    }
}

