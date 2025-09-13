package com.emirerkut.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirerkut.model.Movie
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
    onMovieClick: (Movie) -> Unit = {}
) {
    val query by viewModel.query.collectAsState()
    var active by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(query) {
        if (query.isBlank()) {
            onEvent(SearchScreenEvent.OnIdle)
        } else {
            onEvent(SearchScreenEvent.OnLoading)
            delay(1000)
            onEvent(SearchScreenEvent.OnSearchClick)
        }
    }

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        SearchBarComposable(
            query = query,
            onQueryChange = { newQuery -> viewModel.updateQuery(newQuery) },
            active = true,
            onActiveChange = { active = true },
            onCloseClick = {
                scope.launch {
                    viewModel.updateQuery("")
                    active = true
                    onEvent(SearchScreenEvent.OnIdle)
                }
            },
        ) {
            when (searchState) {
                is SearchState.Success -> MovieGridList(
                    movies = searchState.movies,
                    onRetry = { /* No retry needed */ },
                    onMovieClick = onMovieClick
                )
                is SearchState.Loading -> LoadingScreen()
                else -> Unit
            }
        }

        AnimatedContent(
            targetState = searchState,
            label = "SearchResultsAnimation"
        ) { state ->
            when (state) {
                is SearchState.Idle -> Unit
                is SearchState.Loading -> Unit
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

