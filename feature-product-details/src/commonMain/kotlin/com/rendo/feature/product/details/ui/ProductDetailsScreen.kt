package com.rendo.feature.product.details.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.product.details.domain.mvi.ProductDetailsPayload
import com.rendo.feature.product.details.ui.composable.ProductDetailsScreenComposable
import org.koin.core.parameter.parametersOf

data class ProductDetailsScreen(val payload: ProductDetailsPayload) : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<ProductDetailsScreenModel> { parametersOf(payload) }
        ProductDetailsScreenComposable(screenModel)
    }
}