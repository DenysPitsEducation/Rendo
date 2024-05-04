package com.rendo.feature.rents.di

import cafe.adriel.voyager.navigator.Navigator

interface RentsRouter {
    fun navigateToProductDetails(navigator: Navigator, id: Long)
}