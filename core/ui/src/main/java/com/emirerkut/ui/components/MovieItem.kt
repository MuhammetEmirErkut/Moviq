package com.emirerkut.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.model.Movie
import com.emirerkut.ui.R

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit = {},
    width: Dp = Dimens.default.cardWidthM,
    height: Dp = Dimens.default.cardHeightM,
    ) {
    val dimens: Dimens = Dimens.default

    Box(
        modifier = Modifier
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