package com.emirerkut.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.emirerkut.ui.R

@ExperimentalMaterial3Api
@Composable
fun SearchBarComposable(
    query: String,
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onCloseClick: () -> Unit,
    content: @Composable () -> Unit
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {  },
        active = active,
        onActiveChange = onActiveChange,
        placeholder = { Text(stringResource(R.string.search_movies)) },
        trailingIcon = {
            if (active) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_search)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        content()
    }
}
