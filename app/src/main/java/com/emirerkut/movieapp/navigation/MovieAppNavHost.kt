package com.emirerkut.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import com.emirerkut.movieapp.AppState
import com.emirerkut.home.navigation.Home
import com.emirerkut.home.navigation.homeScreen
import com.emirerkut.movieapp.R


@Composable
fun MovieAppNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    val unexpectedError = stringResource(R.string.unexpected_error_occurred)
    NavHost(
        navController = navController,
        startDestination = Home(),
        modifier = modifier
    ) {
        homeScreen(
            whenErrorOccurred = { throwable, label ->
                onShowSnackbar(throwable.message ?: unexpectedError, label)
            }
        )
    }
}