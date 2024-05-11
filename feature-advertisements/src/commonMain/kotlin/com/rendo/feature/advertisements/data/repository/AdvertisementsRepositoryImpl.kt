package com.rendo.feature.advertisements.data.repository

import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel
import com.rendo.feature.advertisements.domain.repository.AdvertisementsRepository

internal class AdvertisementsRepositoryImpl : AdvertisementsRepository {
    override fun getAdvertisements(): List<AdvertisementDomainModel> {
        return listOf(
            AdvertisementDomainModel(
                id = "2",
                productId = "2",
                name = "DreamGoggles VR",
                imageUrl = "https://picsum.photos/200?random=1",
                price = 149.99,
                currency = "₴",
                phoneNumber = "+380951234567"
            ),
            AdvertisementDomainModel(
                id = "3",
                productId = "3",
                name = "EcoBot 5000",
                imageUrl = "https://picsum.photos/200?random=2",
                price = 79.99,
                currency = "₴",
                phoneNumber = "+380951234567"
            ),
        )
    }
}