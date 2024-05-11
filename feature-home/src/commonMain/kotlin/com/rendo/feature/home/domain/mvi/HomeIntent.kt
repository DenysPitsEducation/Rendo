package com.rendo.feature.home.domain.mvi

internal sealed class HomeIntent {
    data class SearchInputChanged(val input: String) : HomeIntent()
    data class ProductClicked(val id: String) : HomeIntent()
    data class FavoriteButtonClicked(val id: String) : HomeIntent()
}
