package com.rendo.feature.product.details.domain.repository

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

internal interface ProductDetailsRepository {
    suspend fun getProductDetails(id: String): Result<ProductDetailsDomainModel>
    suspend fun createProductRent(productDetails: ProductDetailsDomainModel, tenantPhoneNumber: String): Result<Unit>
}