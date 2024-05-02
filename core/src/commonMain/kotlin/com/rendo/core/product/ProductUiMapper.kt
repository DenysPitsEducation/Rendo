package com.rendo.core.product

import com.rendo.core.utils.formatPrice

class ProductUiMapper {
    fun mapToUiModel(model: ProductDomainModel): ProductUiModel = model.run {
        return ProductUiModel(
            id = id,
            name = name,
            imageUrl = imageUrl,
            price = price.formatPrice(currency),
            isInFavorites = isInFavorites
        )
    }
}