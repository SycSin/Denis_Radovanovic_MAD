package com.example.movie.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movie.data.Movie
import com.example.movie.data.getMovies

class MovieViewModel : ViewModel() {
    private val _movies = getMovies().toMutableStateList()
    val movies: List<Movie>
        get() = _movies

    private val _favoritesList: MutableList<Movie> = mutableStateListOf()
    val favoritesList: List<Movie>
        get() = _favoritesList


    fun updateFavorites(movie: Movie) {
        movies.find { item ->
            item.id == movie.id
        }?.let {
            val favoriteState: Boolean = movie.isFavorite
            movie.isFavorite = !favoriteState

            if(!favoriteState) {
                _favoritesList.add(movie)
            }
            else{
                _favoritesList.remove(movie)
            }
        }
    }

    private fun resetFavoritesList() {
        _favoritesList.clear()
    }

    init {
        resetFavoritesList()
    }
}