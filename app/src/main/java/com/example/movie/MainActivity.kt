package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.MovieViewModel
import com.example.movie.ui.navigation.SetupNavGraph
import com.example.movie.ui.theme.MovieTheme


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    movieViewModel = viewModel()
                    navController = rememberNavController()
                    Column {
                        SetupNavGraph(movieViewModel, navController)
                    }
                }
            }
        }
    }
}

