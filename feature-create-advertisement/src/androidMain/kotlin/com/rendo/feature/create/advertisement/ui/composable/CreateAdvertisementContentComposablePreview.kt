package com.rendo.feature.create.advertisement.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.core.theme.PreviewContainer
import com.rendo.feature.create.advertisement.ui.model.CreateAdvertisementUiModel
import com.rendo.feature.create.advertisement.ui.model.TextFieldUiModel

@Composable
@Preview
private fun CreateAdvertisementScreenPreview() {
    PreviewContainer {
        CreateAdvertisementContentComposable(
            uiModel = CreateAdvertisementUiModel.Content(
                productName = TextFieldUiModel("", null),
                productDescription = TextFieldUiModel("", null),
                productPrice = TextFieldUiModel("", null),
                ownerName = TextFieldUiModel("", null),
                ownerPhoneNumber = TextFieldUiModel("", null),
            ),
            onUserInteraction = {})
    }
}