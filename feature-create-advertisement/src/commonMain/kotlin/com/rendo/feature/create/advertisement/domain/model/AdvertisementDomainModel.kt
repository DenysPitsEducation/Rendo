package com.rendo.feature.create.advertisement.domain.model

/**
 * @param ownerPhoneNumber full format. Example: 380951234567
 */
internal data class AdvertisementDomainModel(
    val productName: String,
    val productDescription: String,
    val productPrice: Double,
    val ownerName: String,
    val ownerPhoneNumber: String,
)
