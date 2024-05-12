package com.rendo.feature.create.advertisement.domain.mvi

import com.rendo.feature.create.advertisement.domain.model.InputType

internal sealed class CreateAdvertisementIntent {
    data class InputChanged(val input: String, val type: InputType) : CreateAdvertisementIntent()
    data object CreateAdvertisementButtonClicked : CreateAdvertisementIntent()
}
