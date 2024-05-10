package com.rendo.feature.create.rent.domain.mvi

internal sealed class CreateRentLabel {
    data object ShowSuccessfulCreationDialog : CreateRentLabel()
}
