package com.rendo.feature.favorites.ui.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.rendo.core.product.ProductUiModel
import com.rendo.core.product.ProductVerticalGrid
import com.rendo.feature.favorites.domain.mvi.FavoritesIntent
import com.rendo.feature.favorites.ui.OnUserInteraction

@Composable
internal fun FavoritesContentComposable(
    products: List<ProductUiModel>,
    onUserInteraction: OnUserInteraction
) {
    ProductVerticalGrid(
        lazyGridState = rememberLazyGridState(),
        products = products,
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp),
        onProductClick = { onUserInteraction(FavoritesIntent.ProductClicked(it)) },
        onFavoriteButtonClick = { onUserInteraction(FavoritesIntent.FavoriteStateRemoved(it)) },
    )
}