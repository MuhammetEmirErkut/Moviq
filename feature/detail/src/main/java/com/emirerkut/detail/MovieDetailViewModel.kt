package com.emirerkut.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirerkut.common.model.Failure
import com.emirerkut.detail.model.MovieDetailUiState
import com.emirerkut.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    fun onEvent(event: MovieDetailScreenEvent) {
        when (event) {
            is MovieDetailScreenEvent.LoadMovieDetail -> {
                loadMovieDetail(event.movieId)
            }
            MovieDetailScreenEvent.RetryLoadMovieDetail -> {
                val currentMovieId = _uiState.value.movie?.id
                if (currentMovieId != null) {
                    loadMovieDetail(currentMovieId)
                }
            }
        }
    }

    private fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            getMovieDetailUseCase(movieId)
                .catch { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = throwable as? Failure ?: Failure(com.emirerkut.common.model.ErrorType.UNKNOWN)
                    )
                }
                .collect { movie ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movie = movie,
                        error = null
                    )
                }
        }
    }
}

sealed class MovieDetailScreenEvent {
    data class LoadMovieDetail(val movieId: Int) : MovieDetailScreenEvent()
    object RetryLoadMovieDetail : MovieDetailScreenEvent()
}
