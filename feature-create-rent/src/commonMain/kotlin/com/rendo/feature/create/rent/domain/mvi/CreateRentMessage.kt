package com.rendo.feature.create.rent.domain.mvi

import com.rendo.feature.create.rent.domain.model.InputDomainModel
import com.rendo.feature.create.rent.domain.model.InputType

sealed class CreateRentMessage {
    data class InputUpdated(val input: InputDomainModel, val type: InputType) : CreateRentMessage()
}
