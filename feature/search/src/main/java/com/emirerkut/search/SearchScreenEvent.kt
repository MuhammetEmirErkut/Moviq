package com.emirerkut.search

interface SearchScreenEvent {
    data object OnTryAgainClick : SearchScreenEvent
    data object OnSearchClick : SearchScreenEvent
    data object OnIdle : SearchScreenEvent
}