package com.emirerkut.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import com.emirerkut.movieapp.AppState
import com.emirerkut.home.navigation.Home
import com.emirerkut.home.navigation.homeScreen
import com.emirerkut.detail.navigation.MovieDetail
import com.emirerkut.detail.navigation.movieDetailScreen
import com.emirerkut.detail.navigation.navigateToMovieDetail
import com.emirerkut.search.navigation.Search
import com.emirerkut.search.navigation.searchScreen
import com.emirerkut.model.Movie
import com.emirerkut.movieapp.R
import kotlinx.coroutines.launch


@Composable
fun MovieAppNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    val unexpectedError = stringResource(R.string.unexpected_error_occurred)
    val scope = rememberCoroutineScope()

    val onMovieClick: (Movie) -> Unit = { movie ->
        val previousRoute = navController.currentBackStackEntry?.destination?.route
        val navOptions = if (previousRoute == "home") {
            androidx.navigation.NavOptions.Builder()
                .setPopUpTo("home", false)
                .setLaunchSingleTop(true)
                .build()
        } else {
            null
        }
        navController.navigateToMovieDetail(
            movieId = movie.id,
            navOptions = navOptions
        )
    }
    
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        homeScreen(
            whenErrorOccurred = { throwable, label ->
                scope.launch {
                    onShowSnackbar(throwable.message ?: unexpectedError, label)
                }
            },
            onMovieClick = onMovieClick,
            navController = navController
        )
        
        movieDetailScreen(
            whenErrorOccurred = { throwable, label ->
                scope.launch {
                    onShowSnackbar(throwable.message ?: unexpectedError, label)
                }
            },
            navController = navController
        )
        
        searchScreen(
            whenErrorOccurred = { throwable, label ->
                scope.launch {
                    onShowSnackbar(throwable.message ?: unexpectedError, label)
                }
            },
            onMovieClick = onMovieClick,
            navController = navController
        )
    }
}