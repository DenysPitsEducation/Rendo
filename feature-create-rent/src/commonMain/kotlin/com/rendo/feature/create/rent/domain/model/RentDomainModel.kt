package com.rendo.feature.create.rent.domain.model

/**
 * @param ownerPhoneNumber full format. Example: 380951234567
 */
data class RentDomainModel(
    val productName: String,
    val productDescription: String,
    val productPrice: Double,
    val ownerName: String,
    val ownerPhoneNumber: String,
)
