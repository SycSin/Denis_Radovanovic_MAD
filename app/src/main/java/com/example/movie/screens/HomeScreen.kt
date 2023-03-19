package com.example.movie.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movie.models.Movie
import com.example.movie.models.getMovies

val defaultMovie = Movie(id = "tt0499549", title = "Avatar", year = "2009", genre = "Action, Adventure, Fantasy", director = "James Cameron", actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang", plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"), rating = "7.9")

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Column{
        HomeScreenAppBar("Movies", navController)
        MovieList(getMovies(), navController)
    }
}

@Composable
fun HomeScreenAppBar(title: String = "Movies", navController: NavHostController) {
    var optionsState by remember {
        mutableStateOf(false)
    }
    val menuItems = listOf("Favorites")
    Row(modifier = Modifier
        .background(Color.Blue)
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(title, style = MaterialTheme.typography.h6, color = Color.White)
        Column {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Settings", tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    optionsState = !optionsState
                }),
            )
            DropdownMenu(
                expanded = optionsState,
                onDismissRequest = {
                    optionsState = false
                },
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(onClick = {
                        navController.navigate(Screen.Favorites.route)
                    }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(item)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie> = listOf(defaultMovie), navController: NavHostController) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie){
                navController.navigate("${Screen.Details.route}/${movie.id}")
            }
        }
    }
}
