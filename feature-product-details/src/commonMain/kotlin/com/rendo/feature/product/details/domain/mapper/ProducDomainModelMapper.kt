package com.rendo.feature.product.details.domain.mapper

import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

fun ProductDetailsDomainModel.toProductDomainModel(): ProductDomainModel {
    return ProductDomainModel(
        id = id,
        name = name,
        imageUrl = imageUrls.firstOrNull(),
        price = price,
        currency = currency,
        isInFavorites = isInFavorites
    )
}