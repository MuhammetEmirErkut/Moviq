package com.emirerkut.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.emirerkut.designsystem.theme.Dimens

@Composable
fun MovieDetailRow(
    label: String,
    value: String,
    dimens: Dimens
) {
    Row(
        modifier = Modifier
            .padding(horizontal = dimens.genericM)
            .padding(bottom = dimens.genericS)
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = "$label: ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
