package com.rendo.feature.create.rent.domain.mvi

sealed class CreateRentLabel {
    data object ShowSuccessfulCreationDialog : CreateRentLabel()
}
