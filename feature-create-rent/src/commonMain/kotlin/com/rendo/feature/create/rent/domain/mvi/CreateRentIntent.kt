package com.rendo.feature.create.rent.domain.mvi

import com.rendo.feature.create.rent.domain.model.InputType

internal sealed class CreateRentIntent {
    data class InputChanged(val input: String, val type: InputType) : CreateRentIntent()
    data object CreateRentButtonClicked : CreateRentIntent()
}
