package com.rendo.feature.create.rent.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.core.theme.PreviewContainer
import com.rendo.feature.create.rent.ui.model.CreateRentUiModel
import com.rendo.feature.create.rent.ui.model.TextFieldUiModel

@Composable
@Preview
private fun CreateRentScreenPreview() {
    PreviewContainer {
        CreateRentContentComposable(
            uiModel = CreateRentUiModel(
                productName = TextFieldUiModel("", null),
                productDescription = TextFieldUiModel("", null),
                productPrice = TextFieldUiModel("", null),
                ownerName = TextFieldUiModel("", null),
                ownerPhoneNumber = TextFieldUiModel("", null),
            ),
            onUserInteraction = {})
    }
}