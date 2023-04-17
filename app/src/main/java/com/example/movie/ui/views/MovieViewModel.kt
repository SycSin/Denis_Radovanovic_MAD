package com.example.movie.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.models.Movie
import com.example.movie.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{ movieList ->
                if(!movieList.isNullOrEmpty()) {
                    _movies.value = movieList
                }
            }
        }
    }

    suspend fun deleteMovie(movie: Movie) {
        repository.delete(movie)
    }

    suspend fun deleteAllMovies() {
        repository.deleteAll()
    }
}