package com.emirerkut.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirerkut.common.model.Failure
import com.emirerkut.domain.usecase.GetPopularMoviesUseCase
import com.emirerkut.domain.usecase.GetTopRatedMoviesUseCase
import com.emirerkut.domain.usecase.GetUpcomingMoviesUseCase
import com.emirerkut.home.model.HomeState
import com.emirerkut.home.model.HomeUiState
import com.emirerkut.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val retryTrigger = Channel<Unit>()

    init {
        loadPopularMovies()
        loadTopRatedMovies()
        loadUpcomingMovies()
    }

    private fun <T> loadMovies(
        useCase: (String) -> Flow<List<T>>,
        updateState: (HomeUiState, HomeState) -> HomeUiState
    ) {
        useCase("en")
            .onStart { _uiState.update { updateState(it, HomeState.Loading) } }
            .onEach { movies -> _uiState.update { updateState(it, HomeState.Success(movies as List<Movie>)) } }
            .catch { exception -> _uiState.update { updateState(it, HomeState.Error(Failure(exception))) } }
            .launchIn(viewModelScope)
    }

    fun loadPopularMovies() {
        loadMovies(
            useCase = getPopularMoviesUseCase::execute,
            updateState = { current, state -> current.copy(popular = state) }
        )
    }

    fun loadTopRatedMovies() {
        loadMovies(
            useCase = getTopRatedMoviesUseCase::execute,
            updateState = { current, state -> current.copy(topRated = state) }
        )
    }

    fun loadUpcomingMovies() {
        loadMovies(
            useCase = getUpcomingMoviesUseCase::execute,
            updateState = { current, state -> current.copy(upcoming = state) }
        )
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnTryAgainClick -> {
                retryTrigger.trySend(Unit)
            }
        }
    }

    fun retryLoadPopularMovies() = loadPopularMovies()
    fun retryLoadTopRatedMovies() = loadTopRatedMovies()
    fun retryLoadUpcomingMovies() = loadUpcomingMovies()
}
