package com.emirerkut.home.model

data class HomeUiState(
    val popular: HomeState = HomeState.Loading,
    val topRated: HomeState = HomeState.Loading,
    val upcoming: HomeState = HomeState.Loading,
    val nowPlaying: HomeState = HomeState.Loading
)
