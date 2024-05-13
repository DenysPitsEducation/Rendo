package com.rendo.feature.create.advertisement.domain.mvi

import com.rendo.feature.create.advertisement.domain.model.InputType
import dev.gitlive.firebase.storage.File

internal sealed class CreateAdvertisementIntent {
    data class ImagesSelected(val images: List<File>) : CreateAdvertisementIntent()
    data class InputChanged(val input: String, val type: InputType) : CreateAdvertisementIntent()
    data object CreateAdvertisementButtonClicked : CreateAdvertisementIntent()
}
