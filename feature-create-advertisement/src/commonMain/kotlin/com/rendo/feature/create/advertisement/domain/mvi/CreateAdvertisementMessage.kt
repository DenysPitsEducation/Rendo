package com.rendo.feature.create.advertisement.domain.mvi

import com.rendo.feature.create.advertisement.domain.model.InputDomainModel
import com.rendo.feature.create.advertisement.domain.model.InputType

internal sealed class CreateAdvertisementMessage {
    data class InputUpdated(val input: InputDomainModel, val type: InputType) : CreateAdvertisementMessage()
}
