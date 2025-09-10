package com.emirerkut.movieapp.navigation

enum class Destination(
    val route: String,
    val title: String = "",
    val icon: String = ""
) {
    HOME(
        route = "home",
        title = "Home",
        icon = "home"
    ),
    SEARCH(
        route = "search",
        title = "Search",
        icon = "search"
    ),
    DETAIL(
        route = "detail/{movieId}",
        title = "Detail",
        icon = "detail"
    )
}