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
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.model.Movie
import com.emirerkut.ui.R

@Composable
fun MovieItem(movie: Movie, modifier: Modifier = Modifier) {
    val dimens: Dimens = Dimens.default
    var isTitleVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.large)
            .width(width = dimens.cardWidthM)
            .height(height = dimens.cardHeightM)
            .clickable {
                isTitleVisible = !isTitleVisible
            }
    ) {
        MoviePoster(
            posterPath = movie.posterPath,
            modifier = Modifier
        )
        HoverTitleOverlay(
            title = movie.originalTitle ?: stringResource(R.string.unknown_title),
            isVisible = isTitleVisible,
            modifier = Modifier.align(Alignment.TopStart)
        )
    }
}