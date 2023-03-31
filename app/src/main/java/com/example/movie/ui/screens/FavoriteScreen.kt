package com.example.movie.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movie.data.Movie
import com.example.movie.ui.MovieViewModel

@Composable
fun FavoriteScreen(
        movieViewModel: MovieViewModel,
        navController: NavHostController,
    ) {
    Column {
        SimpleAppBar(navController = navController)
        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.h4.fontSize,
            text = "Favorites"
        )
        Spacer(modifier = Modifier.size(5.dp))
        Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)

        for(movie: Movie in movieViewModel.favoritesList) {
            MovieRow(movie, onFavoriteClick = { movieViewModel.updateFavorites(movie) }) {
                navController.navigate("${Screen.Details.route}/${movie.id}")
            }
        }
    }
}
