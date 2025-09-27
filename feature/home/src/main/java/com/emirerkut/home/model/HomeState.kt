package com.emirerkut.home.model

import com.emirerkut.common.model.Failure
import com.emirerkut.model.Movie
import com.emirerkut.ui.model.MovieSectionUiState

sealed interface HomeState {
    data object Loading : HomeState
    data class Success(val movies: List<Movie>) : HomeState
    data class Error(val failure: Failure) : HomeState
}

fun HomeState.toUiState(): MovieSectionUiState<Movie> =
    when (this) {
        is HomeState.Loading -> MovieSectionUiState(isLoading = true)
        is HomeState.Success -> MovieSectionUiState(movies = movies)
        is HomeState.Error -> MovieSectionUiState(error = failure)
    }