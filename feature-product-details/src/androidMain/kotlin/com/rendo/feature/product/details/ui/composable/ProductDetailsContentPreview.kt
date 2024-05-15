@file:OptIn(ExperimentalMaterial3Api::class)

package com.rendo.feature.product.details.ui.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.core.theme.PreviewContainer
import com.rendo.feature.product.details.ui.model.DatePickerUiModel
import com.rendo.feature.product.details.ui.model.OwnerUiModel
import com.rendo.feature.product.details.ui.model.ProductDetailsUiModel
import com.rendo.feature.product.details.ui.model.TextFieldUiModel

@Composable
@Preview
private fun ProductDetailsContentPreview() {
    PreviewContainer {
        ProductDetailsContentComposable(
            model = ProductDetailsUiModel(
                id = "1",
                name = "HyperDrive 3000",
                description = "It is the description of the HyperDrive 3000 product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/200?random=1",
                    "https://picsum.photos/200?random=2",
                    "https://picsum.photos/200?random=3",
                    "https://picsum.photos/200?random=4"
                ),
                price = "199.99$ / 1 day",
                isInFavorites = true,
                phoneField = TextFieldUiModel("951234567", null),
                pickupDate = "20 May",
                returnDate = "21 May",
                totalPrice = "199.99$",
                ownerUiModel = OwnerUiModel(
                    name = "George",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                datePickerUiModel = DatePickerUiModel(
                    initialPickupDateMillis = 1,
                    initialReturnDateMillis = 1,
                    selectableDates = object : SelectableDates {
                        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                            return true
                        }
                    },
                    isButtonEnabled = true
                )
            ),
            onUserInteraction = {},
        )
    }
}