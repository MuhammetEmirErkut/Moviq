package com.emirerkut.movieapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.emirerkut.movieapp.navigation.MovieAppNavHost
import kotlinx.coroutines.launch


@Composable
fun App(
    appState: AppState = rememberAppState()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val onShowSnackbar: suspend (String, String?) -> Unit = { message, actionLabel ->
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        MovieAppNavHost(
            appState = appState,
            onShowSnackbar = onShowSnackbar,
            modifier = Modifier.padding(it)
        )
    }
}
