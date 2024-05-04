package com.rendo.feature.rents.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.feature.rents.ui.model.RentUiModel
import com.rendo.feature.rents.ui.model.RentsUiModel
import com.rendo.feature.rents.ui.model.StatusUiModel

@Composable
@Preview
private fun RentsScreenPreview() {
    RentsContentComposable(rentsUiModel = RentsUiModel(
        rentIns = listOf(
            RentUiModel(
                id = 1,
                name = "DreamGoggles VR balbla sasdkljsf kljdalfk jlkdsafj kldsjflk jdslkaj lkfdjalfk sdd",
                imageUrl = "https://picsum.photos/200?random=2",
                status = StatusUiModel("Waiting for acceptance", Color(0xFFCD5915)),
                dates = "27 May - 03 June",
                price = "350 $",
                showAcceptanceButtons = true,
                dropdownItems = emptyList(),
            ),
            RentUiModel(
                id = 2,
                name = "DreamGoggles VR",
                imageUrl = "https://picsum.photos/200?random=3",
                status = StatusUiModel("Accepted", Color(0xFF188711)),
                dates = "27 May - 03 June",
                price = "350 $",
                showAcceptanceButtons = false,
                dropdownItems = emptyList(),
            )
        ),
        rentOuts = listOf(),
    ), onUserInteraction = {})
}