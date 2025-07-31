package com.emirerkut.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.emirerkut.movieapp.AppState
import com.emirerkut.home.navigation.Home
import com.emirerkut.home.navigation.homeScreen


@Composable
fun MovieAppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = Home(),
        modifier = modifier
    ) {
        homeScreen()
    }
}