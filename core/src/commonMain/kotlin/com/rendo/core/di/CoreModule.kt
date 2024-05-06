package com.rendo.core.di

import com.rendo.core.data.repository.ThemeRepositoryImpl
import com.rendo.core.domain.repository.ThemeRepository
import com.rendo.core.domain.usecase.GetUiModeFlowUseCase
import com.rendo.core.domain.usecase.SaveUiModeUseCase
import com.rendo.core.product.ProductUiMapper
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun Module.nativeDependencies()

fun coreModule() = module {
    nativeDependencies()

    factory {
        ProductUiMapper()
    }

    factory {
        GetUiModeFlowUseCase(
            themeRepository = get(),
        )
    }

    factory {
        SaveUiModeUseCase(
            themeRepository = get(),
        )
    }

    factory<ThemeRepository> {
        ThemeRepositoryImpl(
            dataStore = get(),
        )
    }
}