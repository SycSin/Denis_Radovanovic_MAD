package com.example.movie.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movie.models.getMovies

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Column {
        SimpleAppBar(navController = navController)
        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.h4.fontSize,
            text = "Favorites"
        )
        Spacer(modifier = Modifier.size(5.dp))
        Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)

        MovieRow(getMovies()[2]) {
            navController.navigate("${Screen.Details.route}/${getMovies()[2].id}")
        }
        MovieRow(getMovies()[5]){
            navController.navigate("${Screen.Details.route}/${getMovies()[5].id}")
        }
        MovieRow(getMovies()[6]){
            navController.navigate("${Screen.Details.route}/${getMovies()[6].id}")
        }
    }
}
