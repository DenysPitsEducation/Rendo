package com.rendo.feature.favorites.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.favorites.domain.mvi.FavoritesBootstrapper
import com.rendo.feature.favorites.domain.mvi.FavoritesExecutor
import com.rendo.feature.favorites.domain.mvi.FavoritesReducer
import com.rendo.feature.favorites.domain.mvi.FavoritesState
import com.rendo.feature.favorites.ui.FavoritesScreenModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val FAVORITES_STORE_NAME = "FavoritesStore"

fun featureFavoritesModule() = module {
    factory {
        FavoritesScreenModel(store = get(named(FAVORITES_STORE_NAME)))
    }

    factory(named(FAVORITES_STORE_NAME)) {
        DefaultStoreFactory().create(
            name = FAVORITES_STORE_NAME,
            initialState = FavoritesState(
                products = emptyList(),
            ),
            executorFactory = { get<FavoritesExecutor>() },
            reducer = get<FavoritesReducer>(),
            bootstrapper = get<FavoritesBootstrapper>(),
        )
    }

    factory {
        FavoritesBootstrapper(getFavoritesFlowUseCase = get())
    }

    factory {
        FavoritesExecutor(
            removeFavoriteUseCase = get(),
            refreshFavoriteProductsUseCase = get(),
        )
    }

    factory {
        FavoritesReducer()
    }
}
