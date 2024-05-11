package com.rendo.feature.advertisements.domain.model

internal data class AdvertisementDomainModel(
    val id: String,
    val productId: String,
    val name: String,
    val imageUrl: String?,
    val price: Double,
    val currency: String,
    val phoneNumber: String,
)
