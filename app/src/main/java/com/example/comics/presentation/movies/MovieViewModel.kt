package com.example.comics.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.domain.model.Movie
import com.example.comics.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val movies = getMoviesUseCase()
                _uiState.value = _uiState.value.copy(movies = movies, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(movies = emptyList(), isLoading = false)
            }
        }
    }

    data class MovieUiState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}
