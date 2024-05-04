package com.rendo.core.di

import com.rendo.core.product.ProductUiMapper
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun Module.nativeDependencies()

fun coreModule() = module {
    nativeDependencies()

    factory {
        ProductUiMapper()
    }
}