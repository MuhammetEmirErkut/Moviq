package com.emirerkut.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirerkut.search.model.SearchState
import com.emirerkut.ui.components.ErrorScreen
import com.emirerkut.ui.components.LoadingScreen
import com.emirerkut.ui.components.MovieGridList
import com.emirerkut.ui.components.SearchBarComposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchState: SearchState,
    viewModel: SearchViewModel = hiltViewModel(),
    onEvent: (SearchScreenEvent) -> Unit,
    whenErrorOccured: suspend (Throwable, String?) -> Unit = { _, _ -> },
) {
    val query by viewModel.query.collectAsState()
    var active by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(query) {
        if (query.isBlank()) {
            onEvent(SearchScreenEvent.OnIdle)
        } else {
            delay(1000)
            onEvent(SearchScreenEvent.OnSearchClick)
        }
    }

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        SearchBarComposable(
            query = query,
            onQueryChange = { newQuery -> viewModel.updateQuery(newQuery) },
            active = active,
            onActiveChange = { isActive -> active = isActive },
            onCloseClick = {
                scope.launch {
                    viewModel.updateQuery("")
                    active = false
                    onEvent(SearchScreenEvent.OnIdle)
                }
            }
        ) {
            if (searchState is SearchState.Success) {
                MovieGridList(movies = searchState.movies) { }
            }
        }

        AnimatedContent(
            targetState = searchState,
            label = "SearchResultsAnimation"
        ) { state ->
            when (state) {
                is SearchState.Idle -> Unit
                is SearchState.Loading -> LoadingScreen()
                is SearchState.Success -> Unit
                is SearchState.Error -> ErrorScreen(
                    failure = state.failure,
                    onTryAgainClick = { onEvent(SearchScreenEvent.OnTryAgainClick) },
                    whenErrorOccured = whenErrorOccured
                )
            }
        }
    }
}

