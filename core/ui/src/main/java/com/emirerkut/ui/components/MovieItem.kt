package com.emirerkut.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit = {},
    width: Dp = Dimens.default.cardWidthM,
    height: Dp = Dimens.default.cardHeightM,
    scale: Float = 1f,
    alpha: Float = 1f
    ) {
    val dimens: Dimens = Dimens.default

    Box(
        modifier = modifier
            .scale(scale)
            .alpha(alpha)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.large)
            .width(width = width)
            .height(height = height)
            .clickable {
                onMovieClick(movie)
            }
    ) {
        MoviePoster(
            posterPath = movie.posterPath,
            modifier = Modifier
        )
    }
}