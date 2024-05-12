package com.rendo.feature.advertisements.data.mapper

import com.rendo.core.data.model.ProductDataModel
import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel

internal class AdvertisementDomainMapper {
    fun mapToDomainModel(model: ProductDataModel, id: String): AdvertisementDomainModel = model.run {
        AdvertisementDomainModel(
            productId = id,
            name = name,
            imageUrl = imageUrl,
            price = price,
            currency = currency,
        )
    }
}