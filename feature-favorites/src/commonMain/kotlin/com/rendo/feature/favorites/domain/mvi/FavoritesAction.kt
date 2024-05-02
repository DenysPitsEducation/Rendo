package com.rendo.feature.favorites.domain.mvi

import com.rendo.core.product.ProductDomainModel

sealed class FavoritesAction {
    data class FavoritesListUpdated(val products: List<ProductDomainModel>) : FavoritesAction()
}
