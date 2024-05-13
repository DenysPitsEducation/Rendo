package com.rendo.feature.create.advertisement.domain.mvi

import com.rendo.feature.create.advertisement.domain.model.InputDomainModel
import com.rendo.feature.create.advertisement.domain.model.InputType
import dev.gitlive.firebase.storage.File

internal sealed class CreateAdvertisementMessage {
    data class StateUpdated(val state: CreateAdvertisementState) : CreateAdvertisementMessage()
    data class AuthorizationStateUpdated(val isAuthorized: Boolean) : CreateAdvertisementMessage()
    data class ImagesUpdated(val images: List<File>) : CreateAdvertisementMessage()
    data class InputUpdated(val input: InputDomainModel, val type: InputType) : CreateAdvertisementMessage()
}
