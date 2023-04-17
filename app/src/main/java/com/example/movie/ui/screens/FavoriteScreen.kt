package com.example.movie.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movie.models.Movie
import com.example.movie.ui.views.FavoritesViewModel
import com.example.movie.ui.views.MovieViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavHostController,
) {
    val coroutineScope = rememberCoroutineScope()

    val favoritesState by favoritesViewModel.getAllFavorites().collectAsState(initial = emptyList())

    Column {
        SimpleAppBar(title = "My favorite Movies", navController = navController)
        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.h4.fontSize,
            text = "Favorites"
        )
        Spacer(modifier = Modifier.size(5.dp))
        Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)

        for(movie: Movie in favoritesState) {
            MovieRow(movie,
                onFavoriteClick = {
                    coroutineScope.launch {
                        favoritesViewModel.updateFavorites(movie)
                    }
                },
                onDeleteClick = {
                    coroutineScope.launch {
                        movieViewModel.deleteMovie(movie)
                    }
                }
            ) {
                navController.navigate("${Screen.Details.route}/${movie.id}")
            }
        }
    }
}
