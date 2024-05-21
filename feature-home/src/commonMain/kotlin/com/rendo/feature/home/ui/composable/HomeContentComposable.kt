package com.rendo.feature.home.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.itemKey
import com.rendo.core.product.ProductUiModel
import com.rendo.core.product.ProductVerticalGrid
import com.rendo.feature.home.domain.mvi.HomeIntent
import com.rendo.feature.home.ui.OnUserInteraction
import org.jetbrains.compose.resources.stringResource
import rendo.feature_home.generated.resources.Res
import rendo.feature_home.generated.resources.search_placeholder

@Composable
internal fun HomeContentComposable(
    searchInput: String,
    products: LazyPagingItems<ProductUiModel>,
    onUserInteraction: OnUserInteraction
) {
    Column {
        OutlinedTextField(
            value = searchInput,
            onValueChange = { onUserInteraction(HomeIntent.SearchInputChanged(it)) },
            placeholder = {
                Text(stringResource(Res.string.search_placeholder))
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            singleLine = true,
        )
        ProductVerticalGrid(
            itemCount = products.itemCount,
            key = products.itemKey { it.id },
            getProductModel = { products[it]!! },
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp),
            onProductClick = { onUserInteraction(HomeIntent.ProductClicked(it)) },
            onFavoriteButtonClick = { onUserInteraction(HomeIntent.FavoriteButtonClicked(it)) },
        )
    }
}