package com.rendo.core.favorites.domain.usecase

import com.rendo.core.favorites.domain.repository.FavoritesRepository
import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow

class GetFavoritesFlowUseCase(
    private val favoritesRepository: FavoritesRepository,
) {
    fun invoke(): Flow<List<ProductDomainModel>> {
        return favoritesRepository.getFavoriteProductsFlow()
    }
}