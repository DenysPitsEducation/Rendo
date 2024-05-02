package com.rendo.feature.favorites.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.favorites.di.FavoritesRouter
import com.rendo.feature.favorites.domain.mvi.FavoritesLabel
import com.rendo.feature.favorites.ui.FavoritesScreenModel
import com.rendo.feature.favorites.ui.OnUserInteraction
import com.rendo.core.product.ProductUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun FavoritesScreenComposable(screenModel: FavoritesScreenModel) {
    val router: FavoritesRouter = koinInject()
    val stateFlow by screenModel.store.stateFlow.collectAsState()
    val productMapper: ProductUiMapper = koinInject()
    val products = stateFlow.products.map { productMapper.mapToUiModel(it) }
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }
    val navigator = LocalNavigator.currentOrThrow

    FavoritesContentComposable(products, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) {
        when(it) {
            is FavoritesLabel.OpenProductDetails -> {
                router.navigateToProductDetails(navigator, it.id)
            }
        }
    }
}
