package com.emirerkut.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.emirerkut.home.HomeScreen
import com.emirerkut.home.HomeViewModel
import kotlinx.serialization.Serializable
import androidx.compose.runtime.getValue


fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(Home(), navOptions = navOptions)
}


fun NavGraphBuilder.homeScreen(
    whenErrorOccurred: suspend (Throwable, String?) -> Unit,
) {
    composable<Home>() {
        val viewModel: HomeViewModel = hiltViewModel()
        val homeUiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen(
            homeState = homeUiState,
            viewModel = viewModel,
            whenErrorOccured = whenErrorOccurred,
            onEvent = viewModel::onEvent,
        )
    }
}

@Serializable
data class Home(val name: String? = null) {
    companion object {
//        val route = Home::class
    }
}