package com.rendo.core.favorites.domain.usecase

import com.rendo.core.favorites.domain.repository.FavoritesRepository

class RefreshFavoriteProductsUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    suspend fun invoke() {
        return favoritesRepository.refreshFavoriteProducts()
    }
}