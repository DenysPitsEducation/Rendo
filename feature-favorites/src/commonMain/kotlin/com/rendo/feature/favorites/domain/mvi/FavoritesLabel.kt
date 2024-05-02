package com.rendo.feature.favorites.domain.mvi

sealed class FavoritesLabel {
    data class OpenProductDetails(val id: Long) : FavoritesLabel()
}
