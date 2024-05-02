package com.rendo.app.di

import com.rendo.feature.home.di.featureHomeModule
import com.rendo.feature.product.details.di.featureProductDetailsModule

fun getAllKoinModules() = listOf(
    appModule(),
    featureHomeModule(),
    featureProductDetailsModule(),
)