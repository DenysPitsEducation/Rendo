package com.rendo.feature.create.advertisement.domain.mvi

import com.rendo.feature.create.advertisement.domain.model.ImageDomainModel
import com.rendo.feature.create.advertisement.domain.model.InputDomainModel
import com.rendo.feature.create.advertisement.domain.model.InputType

internal sealed class CreateAdvertisementMessage {
    data class StateUpdated(val state: CreateAdvertisementState) : CreateAdvertisementMessage()
    data class AuthorizationStateUpdated(val isAuthorized: Boolean) : CreateAdvertisementMessage()
    data class ImagesUpdated(val images: List<ImageDomainModel>) : CreateAdvertisementMessage()
    data class InputUpdated(val input: InputDomainModel, val type: InputType) : CreateAdvertisementMessage()
}
