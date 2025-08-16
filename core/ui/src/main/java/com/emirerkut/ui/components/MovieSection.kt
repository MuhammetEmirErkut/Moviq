package com.emirerkut.ui.components

import androidx.compose.runtime.Composable
import com.emirerkut.common.model.Failure
import com.emirerkut.model.Movie
import com.emirerkut.ui.model.MovieSectionUiState

@Composable
fun <T> MovieSection(
    title: String,
    state: MovieSectionUiState<T>,
    onRetry: () -> Unit,
    whenErrorOccured: suspend (Throwable, String?) -> Unit
) {
    when {
        state.isLoading -> LoadingScreen()
        state.error != null -> ErrorScreen(
            whenErrorOccured = whenErrorOccured,
            failure = state.error as Failure,
            onTryAgainClick = onRetry
        )
        else -> SectionWithMovieList(
            title = title,
            movies = state.movies as List<Movie>,
            onRetry = onRetry
        )
    }
}
