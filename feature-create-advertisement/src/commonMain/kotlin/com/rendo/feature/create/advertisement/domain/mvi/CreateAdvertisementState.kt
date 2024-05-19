package com.rendo.feature.create.advertisement.domain.mvi

import com.rendo.feature.create.advertisement.domain.model.ImageDomainModel
import com.rendo.feature.create.advertisement.domain.model.InputDomainModel

/**
 * @param ownerPhoneNumber short format. Example: 951234567
 */
internal data class CreateAdvertisementState(
    val isAuthorized: Boolean,
    val images: List<ImageDomainModel>,
    val productName: InputDomainModel,
    val productDescription: InputDomainModel,
    val productPrice: InputDomainModel,
    val productLocation: InputDomainModel,
    val ownerName: InputDomainModel,
    val ownerPhoneNumber: InputDomainModel,
)
