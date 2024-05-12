package com.rendo.feature.create.advertisement.data.mapper

import com.rendo.core.data.model.OwnerDataModel
import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.data.model.ProductDetailsDataModel
import com.rendo.feature.create.advertisement.domain.model.AdvertisementDomainModel
import dev.gitlive.firebase.auth.FirebaseUser

internal class ProductDataMapper {
    fun mapToProductDataModel(model: AdvertisementDomainModel, user: FirebaseUser): ProductDataModel = model.run {
        ProductDataModel(
            name = productName,
            imageUrl = null, // TODO Pits:
            price = productPrice,
            currency = "₴",
            ownerId = user.uid,
        )
    }

    fun mapToProductDetailsDataModel(model: AdvertisementDomainModel, user: FirebaseUser): ProductDetailsDataModel = model.run {
        ProductDetailsDataModel(
            name = productName,
            description = productDescription,
            imageUrls = listOf(), // TODO Pits:
            price = productPrice,
            currency = "₴",
            prohibitedDates = listOf(),
            owner = OwnerDataModel(
                id = user.uid,
                name = ownerName,
                phone = ownerPhoneNumber,
                imageUrl = user.photoURL
            )
        )
    }
}