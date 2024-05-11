package com.rendo.feature.favorites.di

import cafe.adriel.voyager.navigator.Navigator

interface FavoritesRouter {
    fun navigateToProductDetails(navigator: Navigator, id: String)
}