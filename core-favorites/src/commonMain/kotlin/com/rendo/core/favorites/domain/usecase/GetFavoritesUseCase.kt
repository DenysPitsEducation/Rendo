package com.rendo.core.favorites.domain.usecase

import com.rendo.core.favorites.domain.repository.FavoritesRepository
import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    fun invoke(): List<ProductDomainModel> {
        return favoritesRepository.getFavoriteProducts()
    }
}