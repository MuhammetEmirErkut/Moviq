package com.emirerkut.movieapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirerkut.movieapp.navigation.MovieAppNavHost
import com.emirerkut.search.SearchScreen
import com.emirerkut.search.SearchViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    appState: AppState = rememberAppState()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel: SearchViewModel = hiltViewModel()
    val searchState by viewModel.uiState.collectAsState()

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
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Column{
            SearchScreen(
                searchState = searchState,
                viewModel = viewModel,
                onEvent = { event -> viewModel.onEvent(event) },
            )

            MovieAppNavHost(
                appState = appState,
                onShowSnackbar = onShowSnackbar,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}
