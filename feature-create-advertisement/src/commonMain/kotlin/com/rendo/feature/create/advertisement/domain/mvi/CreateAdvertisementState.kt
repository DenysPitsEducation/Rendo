package com.rendo.feature.create.advertisement.domain.mvi

import com.rendo.feature.create.advertisement.domain.model.InputDomainModel
import dev.gitlive.firebase.storage.File

/**
 * @param ownerPhoneNumber short format. Example: 951234567
 */
internal data class CreateAdvertisementState(
    val isAuthorized: Boolean,
    val images: List<File>,
    val productName: InputDomainModel,
    val productDescription: InputDomainModel,
    val productPrice: InputDomainModel,
    val ownerName: InputDomainModel,
    val ownerPhoneNumber: InputDomainModel,
)
