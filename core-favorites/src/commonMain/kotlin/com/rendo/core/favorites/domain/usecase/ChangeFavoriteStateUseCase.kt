package com.rendo.core.favorites.domain.usecase

import com.rendo.core.favorites.domain.repository.FavoritesRepository
import com.rendo.core.product.ProductDomainModel

class ChangeFavoriteStateUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    fun invoke(product: ProductDomainModel) {
        if (product.isInFavorites) {
            favoritesRepository.removeFavorite(product.id)
        } else {
            favoritesRepository.addFavorite(product = product.copy(isInFavorites = true))
        }
    }
}