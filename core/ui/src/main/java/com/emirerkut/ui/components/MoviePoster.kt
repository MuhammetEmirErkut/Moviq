package com.emirerkut.ui.components

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.emirerkut.network.util.Constants
import com.emirerkut.ui.R

@Composable
fun MoviePoster(posterPath: String?, modifier: Modifier = Modifier) {
    val imageUrl = "${Constants.IMAGE_URL}$posterPath"
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.movie_poster),
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
