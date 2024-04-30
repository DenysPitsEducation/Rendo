package com.rendo.feature.product.details.domain.repository

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

interface ProductDetailsRepository {
    fun getProductDetails(id: Long): ProductDetailsDomainModel
}