package com.emirerkut.movieapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.emirerkut.movieapp.navigation.MovieAppNavHost


@Composable
fun App(
    appState: AppState = rememberAppState()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ){
        MovieAppNavHost(
            appState = appState,
            modifier = Modifier.padding(it)
        )
    }
}
