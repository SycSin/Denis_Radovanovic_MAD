package com.example.movie.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.models.Genre
import com.example.movie.models.Movie
import com.example.movie.repositories.MovieRepository
import kotlinx.coroutines.launch


class AddMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch {
        }
    }

    data class ListItemSelectable(
        val title: Genre,
        val isSelected: Boolean
    )

    fun isValidMovie(title: String, year: String, genres: String, director: String, actors: String, rating: Float): Boolean {
        return (title.isNotBlank() && year.isNotBlank() && genres.isNotBlank() && director.isNotBlank() && actors.isNotBlank() && rating > 0.0f)
    }

    suspend fun addMovie(movie: Movie) {
        repository.add(movie)
    }
}