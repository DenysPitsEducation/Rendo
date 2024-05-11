package com.rendo.feature.favorites.domain.mvi

sealed class FavoritesIntent {
    data class ProductClicked(val id: String) : FavoritesIntent()
    data class FavoriteStateRemoved(val id: String) : FavoritesIntent()
}
