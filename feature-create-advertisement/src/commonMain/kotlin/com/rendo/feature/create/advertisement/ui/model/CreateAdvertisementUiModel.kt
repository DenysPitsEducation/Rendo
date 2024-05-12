package com.rendo.feature.create.advertisement.ui.model

internal sealed class CreateAdvertisementUiModel {
    data object AuthorizationRequirement : CreateAdvertisementUiModel()

    data class Content(
        val productName: TextFieldUiModel,
        val productDescription: TextFieldUiModel,
        val productPrice: TextFieldUiModel,
        val ownerName: TextFieldUiModel,
        val ownerPhoneNumber: TextFieldUiModel,
    ) : CreateAdvertisementUiModel()
}
