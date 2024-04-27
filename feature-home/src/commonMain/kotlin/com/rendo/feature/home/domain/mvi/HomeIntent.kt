package com.rendo.feature.home.domain.mvi

sealed class HomeIntent {
    data class SearchInputChanged(val input: String) : HomeIntent()
}
