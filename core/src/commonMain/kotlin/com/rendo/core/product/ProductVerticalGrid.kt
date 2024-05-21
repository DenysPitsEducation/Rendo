package com.rendo.core.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProductVerticalGrid(
    lazyGridState: LazyGridState,
    products: List<ProductUiModel>,
    contentPadding: PaddingValues,
    onProductClick: (id: String) -> Unit,
    onFavoriteButtonClick: (id: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyGridState,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products, key = { it.id }) { model ->
            val itemsInRow by remember {
                derivedStateOf {
                    val row =
                        lazyGridState.layoutInfo.visibleItemsInfo.find { it.key == model.id }?.row
                    lazyGridState.layoutInfo.visibleItemsInfo.filter { it.row == row }
                }
            }
            val maxHeightInRow = itemsInRow.maxOfOrNull { it.size.height }
            val maxHeightInRowDp =
                with(LocalDensity.current) { maxHeightInRow?.toDp() } ?: Dp.Unspecified
            ProductComposable(
                model = model,
                modifier = Modifier.height(maxHeightInRowDp),
                onProductClick = { onProductClick(model.id) },
                onFavoriteButtonClick = { onFavoriteButtonClick(model.id) },
            )
        }
    }
}