package com.rendo.feature.product.details.domain.usecase

import com.rendo.core.favorites.domain.usecase.GetFavoritesUseCase
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository

internal class GetProductDetailsUseCase(
    private val productDetailsRepository: ProductDetailsRepository,
    private val getFavoritesUseCase: GetFavoritesUseCase,
) {
    fun invoke(id: Long): ProductDetailsDomainModel {
        val favoriteProducts = getFavoritesUseCase.invoke()
        return productDetailsRepository.getProductDetails(id)
            .copy(isInFavorites = favoriteProducts.any { it.id == id })
    }
}