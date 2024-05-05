package com.rendo.feature.create.rent.ui.mapper

import androidx.compose.runtime.Composable
import com.rendo.feature.create.rent.domain.mvi.CreateRentState
import com.rendo.feature.create.rent.ui.model.CreateRentUiModel
import com.rendo.feature.create.rent.ui.model.TextFieldUiModel

class CreateRentUiMapper {
    @Composable
    fun mapToUiModel(model: CreateRentState): CreateRentUiModel = model.run {
        return CreateRentUiModel(
            productName = TextFieldUiModel(productName.text, productName.errorText),
            productDescription = TextFieldUiModel(productDescription.text, productDescription.errorText),
            productPrice = TextFieldUiModel(productPrice.text, productPrice.errorText),
            ownerName = TextFieldUiModel(ownerName.text, ownerName.errorText),
            ownerPhoneNumber = TextFieldUiModel(ownerPhoneNumber.text, ownerPhoneNumber.errorText),
        )
    }
}