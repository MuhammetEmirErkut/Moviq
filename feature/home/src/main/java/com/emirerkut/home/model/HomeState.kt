package com.emirerkut.home.model

import com.emirerkut.common.model.Failure
import com.emirerkut.model.Movie

sealed interface HomeState {
    data object Loading : HomeState
    data class Success(val movies: List<Movie>) : HomeState
    data class Error(val failure: Failure) : HomeState
}

