package com.emirerkut.movieapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emirerkut.movieapp.navigation.Destination
import com.emirerkut.movieapp.navigation.Destination.DETAIL
import com.emirerkut.movieapp.navigation.Destination.HOME
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState {
    return remember(navController, coroutineScope) {
        AppState(navController, coroutineScope)
    }
}

class AppState(val navController: NavHostController, val coroutineScope: CoroutineScope) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: Destination?
        @Composable get() = when (currentDestination?.route) {
            HOME.route -> Destination.HOME
            DETAIL.route -> Destination.DETAIL
            else -> null
        }
}