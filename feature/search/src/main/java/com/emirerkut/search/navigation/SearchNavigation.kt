package com.emirerkut.search.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.emirerkut.model.Movie
import com.emirerkut.search.SearchScreen
import com.emirerkut.search.SearchViewModel
import kotlinx.serialization.Serializable
import androidx.compose.runtime.getValue

fun NavController.navigateToSearch(
    navOptions: NavOptions? = null
){
    navigate(Search(), navOptions = navOptions)
}

fun NavGraphBuilder.searchScreen(
    whenErrorOccurred: suspend (Throwable, String?) -> Unit,
    onMovieClick: (Movie) -> Unit = {},
    navController: NavController
) {
    composable("search") {
        val viewModel: SearchViewModel = hiltViewModel()
        val searchUiState by viewModel.uiState.collectAsStateWithLifecycle()
        SearchScreen(
            searchState = searchUiState,
            viewModel = viewModel,
            onEvent = viewModel::onEvent,
            whenErrorOccured = whenErrorOccurred,
            onMovieClick = onMovieClick
        )
    }
}

@Serializable
data class Search(val name: String? = null) {
    val route: String = "search"
}