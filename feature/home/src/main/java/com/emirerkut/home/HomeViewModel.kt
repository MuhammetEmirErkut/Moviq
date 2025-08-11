package com.emirerkut.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirerkut.common.model.Failure
import com.emirerkut.domain.usecase.GetPopularMoviesUseCase
import com.emirerkut.home.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeState>(HomeState.Loading)
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()
    private val retryTrigger = Channel<Unit>()

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        getPopularMoviesUseCase.execute("en") // This will be change
            .onStart {
                _uiState.value = HomeState.Loading
            }
            .onEach { movies ->
                    _uiState.value = HomeState.Success(movies)
            }
            .catch { exception ->
                    _uiState.value = HomeState.Error(Failure(exception))
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnTryAgainClick -> {
                retryTrigger.trySend(Unit)
            }
        }
    }


    fun retry() {
        loadPopularMovies()
    }
}
