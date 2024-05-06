package com.rendo.app.di

import com.rendo.core.di.coreModule
import com.rendo.core.favorites.di.coreFavoritesModule
import com.rendo.feature.create.rent.di.featureCreateRentModule
import com.rendo.feature.favorites.di.featureFavoritesModule
import com.rendo.feature.home.di.featureHomeModule
import com.rendo.feature.product.details.di.featureProductDetailsModule
import com.rendo.feature.profile.di.featureProfileModule
import com.rendo.feature.rents.di.featureRentsModule

fun getAllKoinModules() = listOf(
    appModule(),
    coreModule(),
    coreFavoritesModule(),
    featureCreateRentModule(),
    featureFavoritesModule(),
    featureHomeModule(),
    featureProductDetailsModule(),
    featureProfileModule(),
    featureRentsModule(),
)