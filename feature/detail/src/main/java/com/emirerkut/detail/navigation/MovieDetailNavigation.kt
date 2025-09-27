package com.emirerkut.detail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.emirerkut.detail.MovieDetailScreen
import com.emirerkut.detail.MovieDetailViewModel
import kotlinx.serialization.Serializable
import androidx.compose.runtime.getValue

fun NavController.navigateToMovieDetail(
    movieId: Int,
    navOptions: NavOptions? = null
) {
    navigate("detail/$movieId", navOptions = navOptions)
}

fun NavGraphBuilder.movieDetailScreen(
    whenErrorOccurred: suspend (Throwable, String?) -> Unit,
    navController: NavController
) {
    composable("detail/{movieId}") { backStackEntry ->
        val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
        val viewModel: MovieDetailViewModel = hiltViewModel()
        val detailUiState by viewModel.uiState.collectAsStateWithLifecycle()
        MovieDetailScreen(
            movieId = movieId,
            detailState = detailUiState,
            viewModel = viewModel,
            whenErrorOccured = whenErrorOccurred,
            onEvent = viewModel::onEvent,
            navController = navController
        )
    }
}

@Serializable
data class MovieDetail(val movieId: Int)
