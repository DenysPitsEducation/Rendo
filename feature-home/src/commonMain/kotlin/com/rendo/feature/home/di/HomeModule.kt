package com.rendo.feature.home.di

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.home.data.repository.HomeRepositoryImpl
import com.rendo.feature.home.domain.mvi.HomeAction
import com.rendo.feature.home.domain.mvi.HomeBootstrapper
import com.rendo.feature.home.domain.mvi.HomeExecutor
import com.rendo.feature.home.domain.mvi.HomeReducer
import com.rendo.feature.home.domain.mvi.HomeState
import com.rendo.feature.home.domain.repository.HomeRepository
import com.rendo.feature.home.domain.usecase.GetProductsUseCase
import com.rendo.feature.home.ui.HomeScreenModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val HOME_STORE_NAME = "HomeStore"

fun featureHomeModule() = module {
    factory {
        HomeScreenModel(store = get(named(HOME_STORE_NAME)))
    }

    factory(named(HOME_STORE_NAME)) {
        DefaultStoreFactory().create(
            name = HOME_STORE_NAME,
            initialState = HomeState(
                searchInput = "",
                products = emptyList(),
            ),
            executorFactory = { get<HomeExecutor>() },
            reducer = get<HomeReducer>(),
            bootstrapper = HomeBootstrapper(getFavoritesFlowUseCase = get(), HomeAction.Init),
        )
    }

    factory<HomeExecutor> {
        HomeExecutor(
            getProductsUseCase = get(),
            changeFavoriteStateUseCase = get(),
        )
    }

    factory {
        HomeReducer()
    }

    factory {
        GetProductsUseCase(
            homeRepository = get(),
            getFavoritesUseCase = get(),
        )
    }

    factory<HomeRepository> {
        HomeRepositoryImpl()
    }
}
