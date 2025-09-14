package com.emirerkut.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
fun MovieList(
    movies: List<Movie>,
    onRetry: () -> Unit,
    onMovieClick: (Movie) -> Unit = {}
) {
    val dimens: Dimens = Dimens.default
    val listState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidth = configuration.screenWidthDp.dp

    if (movies.isEmpty()) {
        EmptyMovieList(onRetry = onRetry)
    } else {
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(dimens.genericM),
            horizontalArrangement = Arrangement.spacedBy(Dimens.default.genericS)
        ) {
            itemsIndexed(movies) { index, movie ->
                val itemInfo by remember {
                    derivedStateOf {
                        listState.layoutInfo.visibleItemsInfo.find { it.index == index }
                    }
                }
                
                val itemOffset = itemInfo?.offset ?: 0
                val itemSize = itemInfo?.size ?: 0
                val screenWidthPx = with(density) { screenWidth.toPx() }
                val centerOffset = (screenWidthPx / 2) - (itemOffset + (itemSize / 2))
                val distanceFromCenter = abs(centerOffset)
                val maxDistance = screenWidthPx * 0.6f
                
                val isFirstItem = index == 0
                val isLastItem = index == movies.size - 1
                val isEdgeItem = isFirstItem || isLastItem
                
                val targetScale = if (isEdgeItem) {
                    max(0.9f, min(1f, if (distanceFromCenter < maxDistance) {
                        1f - (distanceFromCenter / maxDistance) * 0.1f
                    } else {
                        0.9f
                    }))
                } else {
                    max(0.85f, min(1f, if (distanceFromCenter < maxDistance) {
                        1f - (distanceFromCenter / maxDistance) * 0.15f
                    } else {
                        0.85f
                    }))
                }
                
                val targetAlpha = if (isEdgeItem) {
                    max(0.8f, min(1f, if (distanceFromCenter < maxDistance) {
                        1f - (distanceFromCenter / maxDistance) * 0.2f
                    } else {
                        0.8f
                    }))
                } else {
                    max(0.6f, min(1f, if (distanceFromCenter < maxDistance) {
                        1f - (distanceFromCenter / maxDistance) * 0.4f
                    } else {
                        0.6f
                    }))
                }
                
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

                Box(
                    modifier = Modifier
                        .offset(y = ((1f - animatedScale) * 20f).dp)
                ) {
                    MovieItem(
                        movie = movie,
                        onMovieClick = onMovieClick,
                        scale = animatedScale,
                        alpha = animatedAlpha
                    )
                }
            }
        }
    }
}