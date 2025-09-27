package com.emirerkut.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirerkut.common.model.Failure
import com.emirerkut.common.model.toLocalizedMessage
import com.emirerkut.home.model.HomeUiState
import com.emirerkut.home.model.toUiState
import com.emirerkut.model.Movie
import com.emirerkut.ui.components.MovieSection

@Composable
fun HomeScreen(
    homeState: HomeUiState,
    viewModel: HomeViewModel = hiltViewModel(),
    onEvent: (HomeScreenEvent) -> Unit,
    whenErrorOccured: suspend (Throwable, String?) -> Unit,
    onMovieClick: (Movie) -> Unit = {}
) {
    val context = LocalContext.current
    
    LaunchedEffect(
        key1 = homeState.popular,
        key2 = homeState.topRated,
        key3 = homeState.upcoming
    ) {
        val firstError = when {
            homeState.popular is com.emirerkut.home.model.HomeState.Error -> homeState.popular.failure
            homeState.topRated is com.emirerkut.home.model.HomeState.Error -> homeState.topRated.failure
            homeState.upcoming is com.emirerkut.home.model.HomeState.Error -> homeState.upcoming.failure
            homeState.nowPlaying is com.emirerkut.home.model.HomeState.Error -> homeState.nowPlaying.failure
            else -> null
        }
        
        firstError?.let { failure ->
            whenErrorOccured(
                failure,
                failure.errorType.toLocalizedMessage(context)
            )
        }
    }
    
    LaunchedEffect(key1 = homeState.nowPlaying) {
        if (homeState.nowPlaying is com.emirerkut.home.model.HomeState.Error) {
            val hasOtherError = homeState.popular is com.emirerkut.home.model.HomeState.Error ||
                    homeState.topRated is com.emirerkut.home.model.HomeState.Error ||
                    homeState.upcoming is com.emirerkut.home.model.HomeState.Error
            
            if (!hasOtherError) {
                whenErrorOccured(
                    homeState.nowPlaying.failure,
                    homeState.nowPlaying.failure.errorType.toLocalizedMessage(context)
                )
            }
        }
    }
    
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            MovieSection(
                title = stringResource(R.string.popular_movies),
                state = homeState.popular.toUiState(),
                onRetry = { viewModel.retryLoadPopularMovies() },
                whenErrorOccured = whenErrorOccured,
                onMovieClick = onMovieClick
            )
        }
        item {

            MovieSection(
                title = stringResource(R.string.top_rated_movies),
                state = homeState.topRated.toUiState(),
                onRetry = { viewModel.retryLoadTopRatedMovies() },
                whenErrorOccured = whenErrorOccured,
                onMovieClick = onMovieClick
            )
        }
        item {
            MovieSection(
                title = stringResource(R.string.upcoming_movies),
                state = homeState.upcoming.toUiState(),
                onRetry = { viewModel.retryLoadUpcomingMovies() },
                whenErrorOccured = whenErrorOccured,
                onMovieClick = onMovieClick
            )
        }
        item {
            MovieSection(
                title = stringResource(R.string.now_playing_movies),
                state = homeState.nowPlaying.toUiState(),
                onRetry = { viewModel.retryLoadNowPlayingMovies() },
                whenErrorOccured = whenErrorOccured,
                onMovieClick = onMovieClick
            )
        }
    }
}


