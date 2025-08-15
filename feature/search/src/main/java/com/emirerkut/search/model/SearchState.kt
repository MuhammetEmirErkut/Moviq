package com.emirerkut.search.model

import com.emirerkut.common.model.Failure
import com.emirerkut.model.Movie

sealed interface SearchState {
    data object Idle : SearchState
    data object Loading: SearchState
    data class Success(val movies: List<Movie>): SearchState
    data class Error(val failure: Failure): SearchState
}