package com.example.comics.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.domain.model.Movie
import com.example.comics.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    var movies by mutableStateOf(emptyList<Movie>())
        private set
    var isLoading by mutableStateOf(false)
        private set

    private var currentPage by mutableStateOf(1)

    var endOfList by mutableStateOf(false)
        private set

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        if (isLoading || endOfList) return
        isLoading = true

        viewModelScope.launch {
            try {
                val newMovies = getMoviesUseCase(page = currentPage)
                if (newMovies.isEmpty()) {
                    endOfList = true
                } else {
                    movies = movies + newMovies
                    currentPage++
                }
            } catch (e: Exception) {
                println("Erro ao buscar filmes: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
}
