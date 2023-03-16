package com.example.movie.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.models.Movie
import com.example.movie.models.getMovies

val defaultMovie = Movie("12", "Test Movie", "2023", "Horror", "Test Director", "Test", "Test", listOf("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.nytimes.com%2F2022%2F09%2F16%2Fmovies%2Fjames-cameron-avatar.html&psig=AOvVaw3uhwhFamWKshgf7Cah5mGm&ust=1678611665191000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCOCAgvLB0_0CFQAAAAAdAAAAABAJ"), "5")

@Composable
fun MovieRow(movie: Movie = defaultMovie, onItemClick: (String) -> Unit = {}) {
    var favoriteState by remember {
        mutableStateOf(false)
    }
    var expandedState by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable() {
            onItemClick(movie.id)
        }
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .build()
                )
                val painterState = painter.state
                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painter,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        //tint = MaterialTheme.colors.secondary,
                        tint = Color.Red,
                        imageVector = if(favoriteState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites",
                        modifier = Modifier.clickable(onClick = {
                            favoriteState = !favoriteState
                        })
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                Icon(
                    imageVector = if (expandedState) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "Show details",
                    modifier = Modifier.clickable(onClick = {
                        expandedState = !expandedState
                    })
                )
            }
            if(expandedState){
                Description(movie)
            }
        }
    }
}

@Composable
fun TitleBar(title: String = "Movies") {
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
        Text(title, style = MaterialTheme.typography.h5, color = Color.White)
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
                        /*TODO when Favorites was clicked*/
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

@Preview
@Composable
fun MovieList(movies: List<Movie> = listOf(defaultMovie), navController: NavHostController) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie){
                Log.d("MyList", "item clicked: ${movie.id}")
                navController.navigate("detail/${movie.id}")
            }
        }
    }
}

@Composable
fun Description(movie: Movie = defaultMovie) {
    val movieDescriptionStyle = TextStyle(
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        fontWeight = FontWeight.Normal,
    )
    val moviePlotDescriptionStyle = TextStyle(
        fontSize = MaterialTheme.typography.subtitle2.fontSize,
        fontWeight = FontWeight.Light,
    )
    Spacer(modifier = Modifier.size(20.dp))
    Text("Director: ${movie.director}", style = movieDescriptionStyle)
    Text("Released: ${movie.year}", style = movieDescriptionStyle)
    Text("Genre: ${movie.genre}", style = movieDescriptionStyle)
    Text("Actors: ${movie.actors}", style = movieDescriptionStyle)
    Text("Rating: ${movie.rating}", style = movieDescriptionStyle)
    Spacer(modifier = Modifier.size(5.dp))
    Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)
    Text("Plot: ${movie.plot}", Modifier.padding(10.dp), style = moviePlotDescriptionStyle)
}

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Column{
        TitleBar("Movies")
        MovieList(getMovies(), navController)
    }
}

@Composable
fun DetailScreen(movieId: String?) {
    movieId?.let {
        Text("Hello Detailscreen $movieId")
    }
}