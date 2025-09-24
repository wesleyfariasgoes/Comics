package com.example.comics.presentation.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comics.R
import com.example.comics.domain.model.Movie
import com.example.comics.presentation.components.HeaderSection
import com.example.comics.presentation.components.MovieItem
import com.example.comics.presentation.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    onMovieClick: (Movie) -> Unit,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val movies = viewModel.movies
    val isLoading = viewModel.isLoading
    val endOfList = viewModel.endOfList

    Scaffold(
        topBar = {
            HeaderSection(
                title = stringResource(R.string.tmdb_movies_title),
                showBackButton = false
            )
        }
    ) { innerPadding ->
        if (movies.isEmpty() && isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                itemsIndexed(movies) { index, movie ->
                    MovieItem(
                        movie = movie,
                        onItemClick = onMovieClick)
                    if (index >= movies.size - 5 && !isLoading && !endOfList) {
                        LaunchedEffect(Unit) {
                            viewModel.fetchMovies()
                        }
                    }
                }

                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}


