package com.rendo.feature.home.domain.usecase

import com.rendo.core.favorites.domain.usecase.GetFavoritesUseCase
import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository

internal class GetProductsUseCase(
    private val homeRepository: HomeRepository,
    private val getFavoritesUseCase: GetFavoritesUseCase,
) {
    suspend fun invoke(lastVisibleProductId: String?): List<ProductDomainModel> {
        val favoriteProducts = getFavoritesUseCase.invoke()
        return homeRepository.getProducts(lastVisibleProductId).map { product ->
            product.copy(isInFavorites = favoriteProducts.any { it.id == product.id })
        }
    }
}