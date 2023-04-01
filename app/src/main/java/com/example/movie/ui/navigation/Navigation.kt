package com.example.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movie.ui.MovieViewModel
import com.example.movie.ui.screens.*

@Composable
fun SetupNavGraph(
    movieViewModel: MovieViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(movieViewModel, navController)
        }
        composable(
            "${Screen.Details.route}/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"), movieViewModel, navController)
        }
        composable(Screen.Favorites.route) {
            FavoriteScreen(movieViewModel, navController)
        }
        composable(Screen.AddMovie.route) {
            AddMovieScreen(Modifier, movieViewModel, navController)
        }
    }
}