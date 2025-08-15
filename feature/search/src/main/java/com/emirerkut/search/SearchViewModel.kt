package com.emirerkut.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirerkut.common.model.Failure
import com.emirerkut.domain.usecase.SearchMoviesUseCase
import com.emirerkut.search.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _uiState = MutableStateFlow<SearchState>(SearchState.Idle)
    val uiState: StateFlow<SearchState> = _uiState.asStateFlow()

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun searchMovies() {
        val currentQuery = _query.value.trim()
        if (currentQuery.isEmpty()) {
            _uiState.value = SearchState.Idle
            return
        }

        viewModelScope.launch {
            searchMoviesUseCase.execute(currentQuery)
                .onStart { _uiState.value = SearchState.Loading }
                .catch { e -> _uiState.value = SearchState.Error(Failure(e)) }
                .collect { movies -> _uiState.value = SearchState.Success(movies) }
        }
    }

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            SearchScreenEvent.OnSearchClick -> searchMovies()
            SearchScreenEvent.OnTryAgainClick -> searchMovies()
            SearchScreenEvent.OnIdle -> _uiState.value = SearchState.Idle
        }
    }
}
