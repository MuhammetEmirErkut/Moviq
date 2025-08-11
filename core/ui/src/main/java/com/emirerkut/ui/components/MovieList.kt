package com.emirerkut.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.model.Movie

@Composable
fun MovieList(
    movies: List<Movie>,
    onRetry: () -> Unit
) {
    val dimens: Dimens = Dimens.default

    if (movies.isEmpty()) {
        EmptyMovieList(onRetry = onRetry)
    } else {
        LazyRow(
            contentPadding = PaddingValues(dimens.genericM),
            horizontalArrangement = Arrangement.spacedBy(Dimens.default.genericS)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie)
            }
        }
    }
}