package com.emirerkut.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirerkut.home.model.HomeUiState
import com.emirerkut.home.model.toUiState
import com.emirerkut.ui.components.MovieSection

@Composable
fun HomeScreen(
    homeState: HomeUiState,
    viewModel: HomeViewModel = hiltViewModel(),
    onEvent: (HomeScreenEvent) -> Unit,
    whenErrorOccured: suspend (Throwable, String?) -> Unit
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        MovieSection(
            title = stringResource(R.string.popular_movies),
            state = homeState.popular.toUiState(),
            onRetry = { viewModel.retryLoadPopularMovies() },
            whenErrorOccured = whenErrorOccured
        )

        MovieSection(
            title = stringResource(R.string.top_rated_movies),
            state = homeState.topRated.toUiState(),
            onRetry = { viewModel.retryLoadTopRatedMovies() },
            whenErrorOccured = whenErrorOccured
        )
    }
}


