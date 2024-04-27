package com.rendo.feature.home.ui.mapper

import com.rendo.core.utils.formatPrice
import com.rendo.feature.home.domain.model.ProductDomainModel
import com.rendo.feature.home.ui.model.ProductUiModel

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