package com.rendo.feature.product.details.di

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.product.details.data.repository.ProductDetailsRepositoryImpl
import com.rendo.feature.product.details.domain.mvi.ProductDetailsAction
import com.rendo.feature.product.details.domain.mvi.ProductDetailsExecutor
import com.rendo.feature.product.details.domain.mvi.ProductDetailsPayload
import com.rendo.feature.product.details.domain.mvi.ProductDetailsReducer
import com.rendo.feature.product.details.domain.mvi.ProductDetailsState
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository
import com.rendo.feature.product.details.domain.usecase.GetProductDetailsUseCase
import com.rendo.feature.product.details.ui.ProductDetailsScreenModel
import com.rendo.feature.product.details.ui.mapper.ProductDetailsUiMapper
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PRODUCT_DETAILS_STORE_NAME = "ProductDetailsStore"

fun featureProductDetailsModule() = module {
    factory { (payload: ProductDetailsPayload) ->
        ProductDetailsScreenModel(store = get(named(PRODUCT_DETAILS_STORE_NAME)) { parametersOf(payload) })
    }

    factory {
        ProductDetailsUiMapper()
    }

    factory(named(PRODUCT_DETAILS_STORE_NAME)) { (payload: ProductDetailsPayload) ->
        DefaultStoreFactory().create(
            name = PRODUCT_DETAILS_STORE_NAME,
            initialState = ProductDetailsState(product = null),
            executorFactory = { get<ProductDetailsExecutor>() },
            reducer = get<ProductDetailsReducer>(),
            bootstrapper = SimpleBootstrapper<ProductDetailsAction>(ProductDetailsAction.Init(payload)),
        )
    }

    factory<ProductDetailsExecutor> {
        ProductDetailsExecutor(getProductDetailsUseCase = get())
    }

    factory {
        ProductDetailsReducer()
    }

    factory {
        GetProductDetailsUseCase(productDetailsRepository = get())
    }

    factory<ProductDetailsRepository> {
        ProductDetailsRepositoryImpl()
    }
}
