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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.emirerkut.detail.model.MovieDetailUiState
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.ui.components.LoadingScreen
import com.emirerkut.ui.components.ErrorScreen
import com.emirerkut.ui.components.MovieDetailRow
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
    val density = LocalDensity.current
    val posterHeightPx = with(density) { dimens.cardHeightL.toPx() }

    LaunchedEffect(movieId) {
        onEvent(MovieDetailScreenEvent.LoadMovieDetail(movieId))
    }

    Box {
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
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimens.cardHeightL)
                    ) {
                        MoviePoster(
                            posterPath = detailState.movie.posterPath,
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            MaterialTheme.colorScheme.background
                                        ),
                                        startY = posterHeightPx * 0.7f,
                                        endY = posterHeightPx
                                    )
                                )
                        )
                    }

                    Text(
                        text = detailState.movie.originalTitle ?: stringResource(R.string.unknown_title),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimens.genericM)
                            .padding(bottom = dimens.genericM)
                    )

                    val overview = detailState.movie.overview
                    if (!overview.isNullOrBlank()) {
                        Text(
                            text = "Overview",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .padding(horizontal = dimens.genericM)
                                .padding(bottom = dimens.genericS)
                        )

                        Text(
                            text = overview,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .padding(horizontal = dimens.genericM)
                                .padding(bottom = dimens.genericM)
                        )
                    }

                    detailState.movie.releaseDate?.let { releaseDate ->
                        MovieDetailRow(
                            label = stringResource(R.string.release_year),
                            value = releaseDate.take(4),
                            dimens = dimens
                        )
                    }

                    detailState.movie.runtime?.let { runtime ->
                        MovieDetailRow(
                            label = stringResource(R.string.duration),
                            value = stringResource(R.string.duration_minutes, runtime),
                            dimens = dimens
                        )
                    }

                    detailState.movie.originalLanguage?.let { language ->
                        MovieDetailRow(
                            label = stringResource(R.string.language),
                            value = language.uppercase(),
                            dimens = dimens
                        )
                    }

                    detailState.movie.spokenLanguages?.let { languages ->
                        if (languages.isNotEmpty()) {
                            val unknownText = stringResource(R.string.unknown)
                            MovieDetailRow(
                                label = stringResource(R.string.subtitle_languages),
                                value = languages.joinToString(", ") {
                                    it.englishName ?: it.name ?: unknownText
                                },
                                dimens = dimens
                            )
                        }
                    }

                    detailState.movie.genres?.let { genres ->
                        if (genres.isNotEmpty()) {
                            MovieDetailRow(
                                label = stringResource(R.string.genres),
                                value = genres.joinToString(", ") { it.name },
                                dimens = dimens
                            )
                        }
                    }

                    detailState.movie.voteAverage?.let { rating ->
                        MovieDetailRow(
                            label = stringResource(R.string.imdb_rating),
                            value = stringResource(
                                R.string.rating_out_of_10,
                                String.format("%.1f", rating)
                            ),
                            dimens = dimens
                        )
                    }

                    detailState.movie.voteCount?.let { voteCount ->
                        MovieDetailRow(
                            label = "",
                            value = stringResource(R.string.vote_count, voteCount),
                            dimens = dimens
                        )
                    }

                    detailState.movie.cast?.let { cast ->
                        if (cast.isNotEmpty()) {
                            val unknownText = stringResource(R.string.unknown)
                            Text(
                                text = stringResource(R.string.cast),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .padding(horizontal = dimens.genericM)
                                    .padding(bottom = dimens.genericS)
                            )

                            cast.take(5).forEach { castMember ->
                                Text(
                                    text = "${castMember.name ?: unknownText} - ${castMember.character ?: unknownText}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    modifier = Modifier
                                        .padding(horizontal = dimens.genericM)
                                        .padding(bottom = 4.dp)
                                )
                            }
                        }
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



