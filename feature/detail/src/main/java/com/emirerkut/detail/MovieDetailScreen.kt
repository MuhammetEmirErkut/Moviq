package com.emirerkut.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.emirerkut.detail.model.MovieDetailUiState
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.ui.components.LoadingScreen
import com.emirerkut.ui.components.ErrorScreen
import com.emirerkut.common.model.Failure
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import com.emirerkut.ui.components.MoviePoster

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    detailState: MovieDetailUiState,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onEvent: (MovieDetailScreenEvent) -> Unit,
    whenErrorOccured: suspend (Throwable, String?) -> Unit,
    navController: NavController? = null
) {
    val dimens: Dimens = Dimens.default

    LaunchedEffect(movieId) {
        onEvent(MovieDetailScreenEvent.LoadMovieDetail(movieId))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = android.R.string.cancel)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                detailState.isLoading -> {
                    LoadingScreen()
                }
                detailState.error != null -> {
                    ErrorScreen(
                        whenErrorOccured = whenErrorOccured,
                        failure = detailState.error as Failure,
                        onTryAgainClick = { onEvent(MovieDetailScreenEvent.RetryLoadMovieDetail) }
                    )
                }
                detailState.movie != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .verticalScroll(rememberScrollState())
                            .padding(dimens.genericM)
                    ) {
                        MoviePoster(
                            posterPath = detailState.movie.posterPath,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(bottom = dimens.genericM)
                        )
                        Text(
                            text = detailState.movie.originalTitle ?: "Unknown Title",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = dimens.genericM)
                        )

                        val overview = detailState.movie.overview
                        if (!overview.isNullOrBlank()) {
                            Text(
                                text = "Overview",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = dimens.genericS)
                            )

                            Text(
                                text = overview,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(bottom = dimens.genericM)
                            )
                        }
                    }
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No movie data available",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}
