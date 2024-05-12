package com.rendo.core.favorites.di

import com.rendo.core.favorites.data.repository.FavoritesRepositoryImpl
import com.rendo.core.favorites.data.source.FavoritesLocalDataSource
import com.rendo.core.favorites.domain.repository.FavoritesRepository
import com.rendo.core.favorites.domain.usecase.ChangeFavoriteStateUseCase
import com.rendo.core.favorites.domain.usecase.GetFavoritesFlowUseCase
import com.rendo.core.favorites.domain.usecase.GetFavoritesUseCase
import com.rendo.core.favorites.domain.usecase.RefreshFavoriteProductsUseCase
import com.rendo.core.favorites.domain.usecase.RemoveFavoriteUseCase
import org.koin.dsl.module

fun coreFavoritesModule() = module {
    factory {
        GetFavoritesUseCase(favoritesRepository = get())
    }

    factory {
        GetFavoritesFlowUseCase(favoritesRepository = get())
    }

    factory {
        ChangeFavoriteStateUseCase(favoritesRepository = get())
    }

    factory {
        RefreshFavoriteProductsUseCase(favoritesRepository = get())
    }

    factory {
        RemoveFavoriteUseCase(favoritesRepository = get())
    }

    factory<FavoritesRepository> {
        FavoritesRepositoryImpl(
            favoritesLocalDataSource = get(),
            productMapper = get(),
        )
    }

    single {
        FavoritesLocalDataSource()
    }
}