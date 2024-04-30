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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.home.di.HomeRouter
import com.rendo.feature.home.domain.mvi.HomeLabel
import com.rendo.feature.home.ui.HomeScreenModel
import com.rendo.feature.home.ui.OnUserInteraction
import com.rendo.feature.home.ui.mapper.ProductUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreenComposable(screenModel: HomeScreenModel) {
    val router: HomeRouter = koinInject()
    val stateFlow by screenModel.store.stateFlow.collectAsState()
    val productMapper: ProductUiMapper = koinInject()
    val products = stateFlow.products.map { productMapper.mapToUiModel(it) }
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }
    val navigator = LocalNavigator.currentOrThrow

    HomeContentComposable(products, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) {
        when(it) {
            is HomeLabel.OpenProductDetails -> {
                router.navigateToProductDetails(navigator, it.id)
            }
        }
    }
}
