package com.rendo.feature.advertisements.domain.model

internal data class AdvertisementDomainModel(
    val productId: String,
    val name: String,
    val imageUrl: String?,
    val price: Double,
    val currency: String,
)
