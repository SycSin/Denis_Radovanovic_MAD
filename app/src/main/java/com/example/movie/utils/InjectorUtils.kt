package com.example.movie.utils

import android.content.Context
import com.example.movie.data.MovieDatabase
import com.example.movie.repositories.MovieRepository
import com.example.movie.ui.views.AddMovieViewModelFactory
import com.example.movie.ui.views.DetailsViewModelFactory
import com.example.movie.ui.views.FavoritesViewModelFactory
import com.example.movie.ui.views.MovieViewModelFactory

object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository{
        return MovieRepository(MovieDatabase.getDatabase(context).MovieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
        return MovieViewModelFactory(getMovieRepository(context))
    }

    fun provideFavoritesViewModelFactory(context: Context): FavoritesViewModelFactory {
        return FavoritesViewModelFactory(getMovieRepository(context))
    }

    fun provideDetailsViewModelFactory(context: Context): DetailsViewModelFactory {
        return DetailsViewModelFactory(getMovieRepository(context))
    }

    fun provideAddMovieViewModelFactory(context: Context): AddMovieViewModelFactory {
        return AddMovieViewModelFactory(getMovieRepository(context))
    }
}