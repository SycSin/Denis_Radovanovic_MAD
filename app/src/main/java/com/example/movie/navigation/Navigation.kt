package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie.screens.DetailScreen
import com.example.movie.screens.FavoriteScreen
import com.example.movie.screens.HomeScreen
import com.example.movie.screens.Screen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            "${Screen.Details.route}/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"), navController)
        }
        composable(Screen.Favorites.route) {
            FavoriteScreen(navController)
        }
    }
}