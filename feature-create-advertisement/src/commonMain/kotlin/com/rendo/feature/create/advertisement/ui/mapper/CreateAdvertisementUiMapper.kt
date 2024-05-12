package com.rendo.feature.create.advertisement.ui.mapper

import androidx.compose.runtime.Composable
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementState
import com.rendo.feature.create.advertisement.ui.model.CreateAdvertisementUiModel
import com.rendo.feature.create.advertisement.ui.model.TextFieldUiModel

internal class CreateAdvertisementUiMapper {
    @Composable
    fun mapToUiModel(model: CreateAdvertisementState): CreateAdvertisementUiModel = model.run {
        return if (model.isAuthorized) {
            CreateAdvertisementUiModel.Content(
                productName = TextFieldUiModel(productName.text, productName.errorText),
                productDescription = TextFieldUiModel(productDescription.text, productDescription.errorText),
                productPrice = TextFieldUiModel(productPrice.text, productPrice.errorText),
                ownerName = TextFieldUiModel(ownerName.text, ownerName.errorText),
                ownerPhoneNumber = TextFieldUiModel(ownerPhoneNumber.text, ownerPhoneNumber.errorText),
            )
        } else {
            CreateAdvertisementUiModel.AuthorizationRequirement
        }
    }
}