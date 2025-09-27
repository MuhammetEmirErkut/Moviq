package com.emirerkut.ui.model

data class MovieSectionUiState<T>(
    val  isLoading: Boolean = false,
    val movies: List<T> = emptyList(),
    val error: Throwable? = null
)