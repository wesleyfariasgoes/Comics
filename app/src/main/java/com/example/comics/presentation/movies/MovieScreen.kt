package com.example.comics.presentation.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.comics.domain.model.Movie
import com.example.comics.ui.theme.ComicsTheme

@Composable
fun MovieScreen(uiState: MovieViewModel.MovieUiState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.movies.isNotEmpty() -> {
                MovieList(movies = uiState.movies)
            }

            else -> {
                Text(text = "Nenhum filme encontrado.")
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.posterPath),
                contentDescription = null,
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = movie.overview ?: "",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview() {
    ComicsTheme {
        MovieScreen(
            uiState = MovieViewModel.MovieUiState(
                isLoading = false,
                movies = listOf(
                    Movie(1, "O Cavaleiro das Trevas", "2008-07-16", ""),
                    Movie(2, "Homem de Ferro", "2008-05-02", "")
                )
            )
        )
    }
}
