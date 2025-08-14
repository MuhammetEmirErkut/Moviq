package com.emirerkut.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirerkut.home.model.HomeState
import com.emirerkut.ui.components.ErrorScreen
import com.emirerkut.ui.components.LoadingScreen
import com.emirerkut.ui.components.SectionWithMovieList


@Composable
fun HomeScreen(
    homeState: HomeState,
    viewModel: HomeViewModel = hiltViewModel(),
    onEvent: (HomeScreenEvent) -> Unit,
    whenErrorOccured: suspend (Throwable, String?) -> Unit
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        AnimatedContent(
            targetState = homeState,
            modifier = Modifier.align(Alignment.Center)
        ) {
            when (it) {
                is HomeState.Loading -> {
                    LoadingScreen()
                }

                is HomeState.Success -> {
                    Column {
                        SectionWithMovieList(
                            title = stringResource(R.string.popular_movies),
                            movies = it.movies,
                            onRetry = { viewModel.retry() }
                        )
                    }
                }


                is HomeState.Error -> {
                    ErrorScreen(
                        whenErrorOccured = whenErrorOccured,
                        failure = it.failure,
                        onTryAgainClick = { onEvent(HomeScreenEvent.OnTryAgainClick) },
                    )
                }
            }
        }

    }
}





