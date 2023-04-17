package com.example.movie.ui.views

import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.T
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.models.Movie
import com.example.movie.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class DetailsViewModel(private val repository: MovieRepository, movieId: Int = 0) : ViewModel() {

    private var _movie = MutableStateFlow(Movie())
    val movie: StateFlow<Movie> = _movie.asStateFlow()

    fun getMovieById(movieId: Int): Movie {
        viewModelScope.launch {
            repository.getMovieById(movieId).collect{ foundMovie ->
                _movie.value = foundMovie
            }
        }
        return movie.value
    }
}