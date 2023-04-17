package com.example.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movie.ui.views.AddMovieViewModel
import com.example.movie.ui.views.DetailsViewModel
import com.example.movie.ui.views.FavoritesViewModel
import com.example.movie.ui.views.MovieViewModel
import com.example.movie.ui.screens.*

@Composable
fun SetupNavGraph(
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    detailsViewModel: DetailsViewModel,
    addMovieViewModel: AddMovieViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(movieViewModel, favoritesViewModel, navController)
        }

        composable(
            "${Screen.Details.route}/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("movieId")
                ?.let { DetailScreen(movie = detailsViewModel.getMovieById(movieId = it), movieViewModel = movieViewModel, favoritesViewModel = favoritesViewModel, navController = navController) }
        }

        composable(Screen.Favorites.route) {
            FavoriteScreen(movieViewModel, favoritesViewModel, navController)
        }

        composable(Screen.AddMovie.route) {
            AddMovieScreen(Modifier, addMovieViewModel, navController)
        }
    }
}