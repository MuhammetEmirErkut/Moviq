package com.emirerkut.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.emirerkut.common.model.Failure
import com.emirerkut.common.model.toLocalizedMessage
import com.emirerkut.designsystem.theme.Dimens
import com.emirerkut.ui.R


@Composable
fun ErrorScreen(
    whenErrorOccured: suspend (Throwable, String?) -> Unit,
    failure: Failure,
    onTryAgainClick: () -> Unit,
) {
    val context = LocalContext.current
    val errorDescription = stringResource(id = R.string.error)
    val dimens: Dimens = Dimens.default

    LaunchedEffect(key1 = true) {
        whenErrorOccured(
            failure,
            failure.errorType.toLocalizedMessage(context)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .semantics { contentDescription = errorDescription }
            .padding(dimens.genericM)
            .fillMaxSize()
    ) {
        Text(
            text = failure.errorType.toLocalizedMessage(context),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimens.genericM))
        TextButton(onClick = onTryAgainClick) {
            Text(text = stringResource(R.string.try_again))
        }
    }

}