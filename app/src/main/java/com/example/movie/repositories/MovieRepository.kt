package com.example.movie.repositories

import com.example.movie.data.MovieDao
import com.example.movie.models.Movie

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun add(movie: Movie) = movieDao.add(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)

    suspend fun update(movie: Movie) = movieDao.update(movie)

    fun getAllMovies() = movieDao.getAllMovies()

    suspend fun deleteAll() = movieDao.deleteAll()

    fun getAllFavorites() = movieDao.getAllFavorites()

    fun getMovieById(movieId: Int) = movieDao.getMovieById(movieId)

}