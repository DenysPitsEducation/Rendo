package com.rendo.feature.advertisements.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.feature.advertisements.ui.model.AdvertisementUiModel
import com.rendo.feature.advertisements.ui.model.AdvertisementsUiModel

@Composable
@Preview
private fun AdvertisementsScreenPreview() {
    AdvertisementsContentComposable(uiModel = AdvertisementsUiModel(
        advertisements = listOf(
            AdvertisementUiModel(
                id = "1",
                name = "DreamGoggles VR balbla sasdkljsf kljdalfk jlkdsafj kldsjflk jdslkaj lkfdjalfk sdd",
                imageUrl = "https://picsum.photos/200?random=2",
                price = "350 ₴",
            ),
            AdvertisementUiModel(
                id = "2",
                name = "DreamGoggles VR",
                imageUrl = "https://picsum.photos/200?random=3",
                price = "350 ₴",
            )
        ),
    ), onUserInteraction = {})
}