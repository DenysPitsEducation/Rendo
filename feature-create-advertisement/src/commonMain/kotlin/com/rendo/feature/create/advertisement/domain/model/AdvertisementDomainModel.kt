package com.rendo.feature.create.advertisement.domain.model

import dev.gitlive.firebase.storage.File

/**
 * @param ownerPhoneNumber full format. Example: 380951234567
 */
internal data class AdvertisementDomainModel(
    val images: List<File>,
    val productName: String,
    val productDescription: String,
    val productPrice: Double,
    val ownerName: String,
    val ownerPhoneNumber: String,
)
