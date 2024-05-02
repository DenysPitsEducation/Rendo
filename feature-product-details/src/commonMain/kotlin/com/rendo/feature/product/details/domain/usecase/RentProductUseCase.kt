package com.rendo.feature.product.details.domain.usecase

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository

class RentProductUseCase(
    private val productDetailsRepository: ProductDetailsRepository,
) {
    fun invoke(product: ProductDetailsDomainModel): Result<Unit> {
        return Result.success(Unit)
    }
}