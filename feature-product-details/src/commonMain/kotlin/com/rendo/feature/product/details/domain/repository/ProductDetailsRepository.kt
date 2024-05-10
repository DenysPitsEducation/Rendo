package com.rendo.feature.product.details.domain.repository

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

internal interface ProductDetailsRepository {
    fun getProductDetails(id: Long): ProductDetailsDomainModel
}