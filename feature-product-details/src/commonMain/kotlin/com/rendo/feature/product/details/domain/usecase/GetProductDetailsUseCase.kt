package com.rendo.feature.product.details.domain.usecase

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository

internal class GetProductDetailsUseCase(
    private val productDetailsRepository: ProductDetailsRepository,
) {
    fun invoke(id: Long): ProductDetailsDomainModel {
        return productDetailsRepository.getProductDetails(id)
    }
}