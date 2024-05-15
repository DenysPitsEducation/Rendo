package com.rendo.feature.favorites.domain.mvi

import com.rendo.core.product.ProductDomainModel

internal sealed class FavoritesAction {
    data class FavoritesListUpdated(val products: List<ProductDomainModel>) : FavoritesAction()
    data class AuthorizationStateUpdated(val isAuthorized: Boolean) : FavoritesAction()
}
