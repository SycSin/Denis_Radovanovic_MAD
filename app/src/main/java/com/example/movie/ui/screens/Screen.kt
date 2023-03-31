package com.example.movie.ui.screens

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Details: Screen(route = "detail_screen")
    object Favorites: Screen(route = "favorite_screen")
}
