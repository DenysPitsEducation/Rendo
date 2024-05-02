package com.rendo.feature.favorites.domain.mvi

sealed class FavoritesIntent {
    data class ProductClicked(val id: Long) : FavoritesIntent()
    data class FavoriteStateRemoved(val id: Long) : FavoritesIntent()
}
