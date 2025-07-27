package com.example.movieapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun App(
//    appState: AppState = rememberAppState()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ){
        NavHost(
            modifier = Modifier.padding(it),
        )
    }
}

@Composable
fun NavHost(modifier: Modifier) {
    TODO("Not yet implemented")
}