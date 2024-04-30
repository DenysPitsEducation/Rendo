package com.rendo.app.di

import cafe.adriel.voyager.navigator.Navigator
import com.rendo.feature.home.di.HomeRouter
import com.rendo.feature.product.details.domain.mvi.ProductDetailsPayload
import com.rendo.feature.product.details.ui.ProductDetailsScreen
import org.koin.dsl.module

fun appModule() = module {
    factory<HomeRouter> {
        object : HomeRouter {
            override fun navigateToProductDetails(navigator: Navigator, id: Long) {
                navigator.push(ProductDetailsScreen(ProductDetailsPayload(id)))
            }
        }
    }
}