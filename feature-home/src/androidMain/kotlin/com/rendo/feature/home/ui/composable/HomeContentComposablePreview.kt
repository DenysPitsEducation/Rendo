package com.rendo.feature.home.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.core.product.ProductUiModel
import com.rendo.core.theme.PreviewContainer

@Composable
@Preview
private fun HomeScreenPreview() {
    PreviewContainer {
        HomeContentComposable(searchInput = "", products = listOf(
            ProductUiModel(
                id = "1",
                name = "HyperDrive 3000",
                imageUrl = "https://picsum.photos/200?random=1",
                price = "199.99 ₴",
                isInFavorites = false
            ),
            ProductUiModel(
                id = "2",
                name = "DreamGoggles VR",
                imageUrl = "https://picsum.photos/200?random=2",
                price = "149.99 ₴",
                isInFavorites = true
            ),
            ProductUiModel(
                id = "3",
                name = "EcoBot 5000",
                imageUrl = "https://picsum.photos/200?random=3",
                price = "79.99 ₴",
                isInFavorites = true
            ),
            ProductUiModel(
                id = "4",
                name = "InfiniteBattery Power Bank",
                imageUrl = "https://picsum.photos/200?random=4",
                price = "49.99 ₴",
                isInFavorites = false
            ),
            ProductUiModel(
                id = "5",
                name = "IntelliToaster",
                imageUrl = "https://picsum.photos/200?random=5",
                price = "29.99 ₴",
                isInFavorites = false
            )
        ), onUserInteraction = {})
    }
}