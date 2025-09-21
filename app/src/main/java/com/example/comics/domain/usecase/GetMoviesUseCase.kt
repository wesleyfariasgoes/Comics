package com.example.comics.domain.usecase

import com.example.comics.domain.model.Movie
import com.example.comics.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
private val repository: MovieRepository
) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getMovies()
    }
}
