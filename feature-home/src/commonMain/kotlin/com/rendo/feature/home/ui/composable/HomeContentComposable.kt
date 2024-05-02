package com.rendo.feature.home.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rendo.feature.home.domain.mvi.HomeIntent
import com.rendo.feature.home.ui.OnUserInteraction
import com.rendo.feature.home.ui.model.ProductUiModel

@Composable
fun HomeContentComposable(searchInput: String, products: List<ProductUiModel>, onUserInteraction: OnUserInteraction) {
    val lazyGridState = rememberLazyGridState()

    Column {
        OutlinedTextField(
            value = searchInput,
            onValueChange = { onUserInteraction(HomeIntent.SearchInputChanged(it)) },
            placeholder = {
                Text("Enter a name of the product")
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            singleLine = true,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = lazyGridState,
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp),
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
                    onUserInteraction = onUserInteraction
                )
            }
        }
    }
}