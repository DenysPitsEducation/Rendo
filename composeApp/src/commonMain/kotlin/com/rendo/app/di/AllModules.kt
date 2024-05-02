package com.rendo.app.di

import com.rendo.core.di.coreModule
import com.rendo.core.favorites.di.coreFavoritesModule
import com.rendo.feature.favorites.di.featureFavoritesModule
import com.rendo.feature.home.di.featureHomeModule
import com.rendo.feature.product.details.di.featureProductDetailsModule

fun getAllKoinModules() = listOf(
    appModule(),
    coreModule(),
    coreFavoritesModule(),
    featureFavoritesModule(),
    featureHomeModule(),
    featureProductDetailsModule(),
)