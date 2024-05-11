package com.rendo.feature.favorites.domain.mvi

internal sealed class FavoritesLabel {
    data class OpenProductDetails(val id: String) : FavoritesLabel()
}
