package com.rendo.feature.home.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rendo.feature.home.domain.mvi.HomeIntent
import com.rendo.feature.home.ui.OnUserInteraction
import com.rendo.core.product.ProductUiModel
import com.rendo.core.product.ProductVerticalGrid

@Composable
fun HomeContentComposable(
    searchInput: String,
    products: List<ProductUiModel>,
    onUserInteraction: OnUserInteraction
) {
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
        ProductVerticalGrid(
            products = products,
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp),
            onProductClick = { onUserInteraction(HomeIntent.ProductClicked(it)) },
            onFavoriteButtonClick = { onUserInteraction(HomeIntent.FavoriteButtonClicked(it)) },
        )
    }
}