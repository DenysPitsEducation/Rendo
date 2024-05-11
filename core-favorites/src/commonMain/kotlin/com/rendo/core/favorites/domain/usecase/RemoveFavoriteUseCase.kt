package com.rendo.core.favorites.domain.usecase

import com.rendo.core.favorites.domain.repository.FavoritesRepository

class RemoveFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    fun invoke(id: String) {
        favoritesRepository.removeFavorite(id = id)
    }
}