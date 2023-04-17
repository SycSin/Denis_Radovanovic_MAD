package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.views.AddMovieViewModel
import com.example.movie.ui.views.DetailsViewModel
import com.example.movie.ui.views.FavoritesViewModel
import com.example.movie.ui.views.MovieViewModel
import com.example.movie.ui.navigation.SetupNavGraph
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.utils.InjectorUtils


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    lateinit var movieViewModel: MovieViewModel
    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var detailsViewModel: DetailsViewModel
    lateinit var addMovieViewModel: AddMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()

                    movieViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(LocalContext.current))
                    favoritesViewModel = viewModel(factory = InjectorUtils.provideFavoritesViewModelFactory(LocalContext.current))
                    detailsViewModel = viewModel(factory = InjectorUtils.provideDetailsViewModelFactory(LocalContext.current))
                    addMovieViewModel = viewModel(factory = InjectorUtils.provideAddMovieViewModelFactory(LocalContext.current))

                    Column {
                        SetupNavGraph(movieViewModel, favoritesViewModel, detailsViewModel, addMovieViewModel, navController)
                    }
                }
            }
        }
    }
}

