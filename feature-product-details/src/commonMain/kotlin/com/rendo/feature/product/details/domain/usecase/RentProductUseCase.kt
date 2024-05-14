package com.rendo.feature.product.details.domain.usecase

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository

internal class RentProductUseCase(
    private val productDetailsRepository: ProductDetailsRepository,
) {
    suspend fun invoke(product: ProductDetailsDomainModel): Result<Unit> {
        return productDetailsRepository.createProductRent(product)
    }
}