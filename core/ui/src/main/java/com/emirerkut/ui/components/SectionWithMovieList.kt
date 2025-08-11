package com.emirerkut.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.model.Movie

@Composable
fun SectionWithMovieList(
    title: String,
    movies: List<Movie>,
    onRetry: () -> Unit
) {
    val dimens: Dimens = Dimens.default
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimens.genericM),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            text = title
        )

        MovieList(
            movies = movies,
            onRetry = onRetry
        )
    }
}
