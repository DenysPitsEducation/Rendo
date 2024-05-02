package com.rendo.feature.home.domain.mvi

sealed class HomeIntent {
    data class SearchInputChanged(val input: String) : HomeIntent()
    data class ProductClicked(val id: Long) : HomeIntent()
    data class FavoriteStateChanged(val id: Long) : HomeIntent()
}
