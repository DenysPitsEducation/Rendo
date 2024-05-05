package com.rendo.feature.create.rent.domain.mvi

import com.rendo.feature.create.rent.domain.model.InputDomainModel

/**
 * @param ownerPhoneNumber short format. Example: 951234567
 */
data class CreateRentState(
    val productName: InputDomainModel,
    val productDescription: InputDomainModel,
    val productPrice: InputDomainModel,
    val ownerName: InputDomainModel,
    val ownerPhoneNumber: InputDomainModel,
)
