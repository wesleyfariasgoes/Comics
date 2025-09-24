package com.example.comics.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.comics.domain.model.Movie
import com.example.comics.domain.model.toMovie
import com.example.comics.presentation.detail.MovieDetailScreen
import com.example.comics.presentation.movies.MovieListScreen
import com.example.comics.presentation.navigation.movieNavType
import com.example.comics.ui.theme.ComicsTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicsTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movie_list_screen") {
        composable("movie_list_screen") {
            MovieListScreen(
                onMovieClick = { movie ->
                    val movieJson = Gson().toJson(movie)
                    val encodedMovieJson = java.net.URLEncoder.encode(movieJson, "UTF-8")
                    navController.navigate("movie_detail_screen/$encodedMovieJson")
                }
            )
        }
        composable(
            "movie_detail_screen/{movie}",
            arguments = listOf(navArgument("movie") { type = movieNavType })
        ) { backStackEntry ->
            val movie =
                backStackEntry.arguments?.getSerializable("movie") as? Movie
                        if (movie != null) {
                MovieDetailScreen(
                    movie = movie.toMovie(),
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}