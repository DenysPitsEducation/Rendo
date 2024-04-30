package com.rendo.feature.home.di

import cafe.adriel.voyager.navigator.Navigator

interface HomeRouter {
    fun navigateToProductDetails(navigator: Navigator, id: Long)
}