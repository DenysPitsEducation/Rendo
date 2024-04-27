package com.rendo.feature.home.domain.usecase

import com.rendo.feature.home.domain.model.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository

internal class GetProductsUseCase(
    private val homeRepository: HomeRepository,
) {
    fun invoke(): List<ProductDomainModel> {
        return homeRepository.getProducts()
    }
}