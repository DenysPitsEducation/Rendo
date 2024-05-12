package com.rendo.core.data.mapper

import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.product.ProductDomainModel

class ProductDomainMapper {
    fun mapToDomainModel(model: ProductDataModel, id: String): ProductDomainModel = model.run {
        return ProductDomainModel(
            id = id,
            name = name,
            imageUrl = imageUrl,
            price = price,
            currency = currency,
            isInFavorites = false,
        )
    }
}