package com.rendo.feature.create.advertisement.domain.mvi

internal sealed class CreateAdvertisementLabel {
    data object ShowSuccessfulCreationDialog : CreateAdvertisementLabel()
}
