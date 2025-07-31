package com.emirerkut.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.emirerkut.home.HomeScreen
import kotlinx.serialization.Serializable

// import com.emirerkut.home.HomeViewModel
// import androidx.hilt.navigation.compose.hiltViewModel

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(Home(), navOptions = navOptions)
}


fun NavGraphBuilder.homeScreen() {
    composable<Home>() {
        // val viewModel: HomeViewModel = hiltViewModel()
        HomeScreen()
    }
}

@Serializable
data class Home(val name: String = "Home"){
    companion object {
//        val route = Home::class
    }
}