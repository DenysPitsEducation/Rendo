package com.rendo.core.di

import com.rendo.core.product.ProductUiMapper
import org.koin.dsl.module

fun coreModule() = module {
    factory {
        ProductUiMapper()
    }
}