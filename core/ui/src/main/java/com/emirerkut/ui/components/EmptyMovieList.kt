package com.emirerkut.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.ui.R

@Composable
fun EmptyMovieList(onRetry: () -> Unit) {
    val dimens: Dimens = Dimens.default

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(dimens.genericM)
        ) {
            Text(
                text = stringResource(R.string.no_movies_found),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = stringResource(R.string.try_again_to_load_movies),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = dimens.genericS)
            )
            Button(onClick = onRetry) {
                Text(stringResource(R.string.refresh))
            }
        }
    }
}