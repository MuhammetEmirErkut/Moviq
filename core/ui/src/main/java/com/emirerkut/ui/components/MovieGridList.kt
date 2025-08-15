package com.emirerkut.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.model.Movie

@Composable
fun MovieGridList(
    movies: List<Movie>,
    onRetry: () -> Unit
) {
    val dimens: Dimens = Dimens.default

    if (movies.isEmpty()) {
        EmptyMovieList(onRetry = onRetry)
    } else {
        val movieChunks = movies.chunked(2)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = dimens.genericL, horizontal = dimens.genericM),
            verticalArrangement = Arrangement.spacedBy(dimens.genericL),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(movieChunks) { chunk ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimens.genericL),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(fraction = 0.9f)
                ) {
                    chunk.forEach { movie ->
                        MovieItem(
                            movie = movie,
                            modifier = Modifier.weight(1f)
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
