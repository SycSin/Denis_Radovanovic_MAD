package com.example.movie.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.models.Movie

@Composable
fun SimpleAppBar(title: String = "Movies", navController: NavHostController) {
    Row(modifier = Modifier
        .background(Color.Blue)
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
    ){
        Row {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return", tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(title, style = MaterialTheme.typography.h6, color = Color.White)
        }
    }
}

@Composable
fun MovieRow(
    movie: Movie = defaultMovie,
    onDeleteClick: (Movie) -> Unit = {},
    onFavoriteClick: (Movie) -> Unit = {},
    onItemClick: (String) -> Unit = {},
) {
    var favoriteState by remember {
        mutableStateOf(movie.isFavorite)
    }
    var deleteState by remember {
        mutableStateOf(false)
    }
    var expandedState by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            onItemClick(movie.id.toString())
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
                    ImageRequest.Builder(LocalContext.current).data(data = movie.images[0]).apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
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
                    Row {
                        Icon(
                            tint = Color.Red,
                            imageVector = if(favoriteState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Add to favorites",
                            modifier = Modifier
                                .clickable {
                                    favoriteState = !favoriteState
                                    onFavoriteClick(movie)
                                }
                        )
                        Icon(
                            tint = Color.Black,
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Movie",
                            modifier = Modifier
                                .clickable {
                                    deleteState = !deleteState
                                    onDeleteClick(movie)
                                }
                        )
                    }
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
