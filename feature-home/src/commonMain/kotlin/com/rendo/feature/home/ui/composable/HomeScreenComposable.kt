package com.rendo.feature.home.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.map
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.product.ProductUiMapper
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.home.di.HomeRouter
import com.rendo.feature.home.domain.mvi.HomeLabel
import com.rendo.feature.home.ui.HomeScreenModel
import com.rendo.feature.home.ui.OnUserInteraction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun HomeScreenComposable(screenModel: HomeScreenModel) {
    val router: HomeRouter = koinInject()
    val stateFlow by screenModel.store.stateFlow.collectAsState()
    val productMapper: ProductUiMapper = koinInject()
    val products = screenModel.store.stateFlow.map {
        it.visibleProducts.map { productMapper.mapToUiModel(it) }
    }.collectAsLazyPagingItems()
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }
    val navigator = LocalNavigator.currentOrThrow

    HomeContentComposable(stateFlow.searchInput, products, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) {
        when(it) {
            is HomeLabel.OpenProductDetails -> {
                router.navigateToProductDetails(navigator, it.id)
            }
        }
    }
}
