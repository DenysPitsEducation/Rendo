package com.rendo.feature.home.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rendo.core.product.ProductUiModel
import com.rendo.core.product.ProductVerticalGrid
import com.rendo.feature.home.domain.mvi.HomeIntent
import com.rendo.feature.home.ui.OnUserInteraction
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource
import rendo.feature_home.generated.resources.Res
import rendo.feature_home.generated.resources.search_placeholder

@Composable
internal fun HomeContentComposable(
    searchInput: String,
    products: List<ProductUiModel>,
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
        val lazyGridState = rememberLazyGridState()

        ProductVerticalGrid(
            lazyGridState = lazyGridState,
            products = products,
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp),
            onProductClick = { onUserInteraction(HomeIntent.ProductClicked(it)) },
            onFavoriteButtonClick = { onUserInteraction(HomeIntent.FavoriteButtonClicked(it)) },
        )

        OnScrollLaunchedEffect(lazyGridState, onUserInteraction)
    }
}

@Composable
internal fun OnScrollLaunchedEffect(
    listState: LazyGridState,
    onUserInteraction: OnUserInteraction
) {
    LaunchedEffect(listState) {
        val visibleItemKeysFlow = snapshotFlow {
            listState.layoutInfo
                .visibleItemsInfo
                .mapNotNull { it.key as? String }
        }

        visibleItemKeysFlow
            .distinctUntilChanged()
            .collect { keys ->
                keys.forEach {
                    onUserInteraction(HomeIntent.ProductWatched(it))
                }
            }
    }
}
