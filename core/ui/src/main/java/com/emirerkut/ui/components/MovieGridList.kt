package com.emirerkut.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.model.Movie
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@Composable
fun MovieGridList(
    movies: List<Movie>,
    onRetry: () -> Unit,
    onMovieClick: (Movie) -> Unit = {}
) {
    val dimens: Dimens = Dimens.default
    val listState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenHeight = configuration.screenHeightDp.dp

    if (movies.isEmpty()) {
        EmptyMovieList(onRetry = onRetry)
    } else {
        val movieChunks = movies.chunked(2)

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = dimens.genericM, horizontal = dimens.genericS),
            verticalArrangement = Arrangement.spacedBy(dimens.genericM),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(movieChunks.size) { chunkIndex ->
                val chunk = movieChunks[chunkIndex]
                val itemInfo by remember {
                    derivedStateOf {
                        listState.layoutInfo.visibleItemsInfo.find { it.index == chunkIndex }
                    }
                }
                
                val itemOffset = itemInfo?.offset ?: 0
                val itemSize = itemInfo?.size ?: 0
                val screenHeightPx = with(density) { screenHeight.toPx() }
                val centerOffset = (screenHeightPx / 2) - (itemOffset + (itemSize / 2))
                val distanceFromCenter = abs(centerOffset)
                val maxDistance = screenHeightPx * 0.6f
                
                val isFirstChunk = chunkIndex == 0
                val isLastChunk = chunkIndex == movieChunks.size - 1
                
                val adjustedDistance = if (isFirstChunk || isLastChunk) {
                    distanceFromCenter * 0.3f
                } else {
                    distanceFromCenter
                }
                
                val targetScale = max(0.85f, min(1f, if (adjustedDistance < maxDistance) {
                    1f - (adjustedDistance / maxDistance) * 0.15f
                } else {
                    0.85f
                }))
                
                val targetAlpha = max(0.6f, min(1f, if (adjustedDistance < maxDistance) {
                    1f - (adjustedDistance / maxDistance) * 0.4f
                } else {
                    0.6f
                }))
                
                val animatedScale by animateFloatAsState(
                    targetValue = targetScale,
                    animationSpec = tween(durationMillis = 100),
                    label = "scale_animation"
                )
                
                val animatedAlpha by animateFloatAsState(
                    targetValue = targetAlpha,
                    animationSpec = tween(durationMillis = 100),
                    label = "alpha_animation"
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimens.genericS),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.9f)
                        .scale(animatedScale)
                        .alpha(animatedAlpha)
                ) {
                    chunk.forEach { movie ->
                        MovieItem(
                            movie = movie,
                            modifier = Modifier.weight(1f).aspectRatio(0.7f),
                            onMovieClick = onMovieClick,
                            width = dimens.cardWidthL,
                            height = dimens.cardHeightL
                        )
                    }

                    if (chunk.size == 1) {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
