package com.emirerkut.home.model

import com.emirerkut.model.Movie

data class HomeUiState(
    val popular: HomeState = HomeState.Loading,
    val topRated: HomeState = HomeState.Loading,
)
