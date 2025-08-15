package com.emirerkut.search.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
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
) {
    composable<Search>() {
        val viewModel: SearchViewModel = hiltViewModel()
        val searchUiState by viewModel.uiState.collectAsStateWithLifecycle()
        SearchScreen(
            searchState = searchUiState,
            viewModel = viewModel,
            onEvent = TODO(),
            whenErrorOccured = whenErrorOccurred
        )
    }
}

@Serializable
data class Search(val name: String? = null) {
    companion object {
//        val route = Home::class
    }
}