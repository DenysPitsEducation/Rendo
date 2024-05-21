package com.rendo.feature.home.domain.usecase

import app.cash.paging.PagingData
import app.cash.paging.map
import com.rendo.core.favorites.domain.usecase.GetFavoritesUseCase
import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GetProductsUseCase(
    private val homeRepository: HomeRepository,
    private val getFavoritesUseCase: GetFavoritesUseCase,
) {
    suspend fun invoke(): Flow<PagingData<ProductDomainModel>> {
        val favoriteProducts = getFavoritesUseCase.invoke()
        return homeRepository.getProducts().map { pagingData ->
            pagingData.map { product ->
                product.copy(isInFavorites = favoriteProducts.any { it.id == product.id })
            }
        }
    }
}