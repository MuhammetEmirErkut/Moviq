package com.emirerkut.detail.model

import com.emirerkut.common.model.Failure
import com.emirerkut.model.Movie

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: Failure? = null
)
