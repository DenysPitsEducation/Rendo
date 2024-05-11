package com.rendo.feature.advertisements.di

import cafe.adriel.voyager.navigator.Navigator

interface AdvertisementsRouter {
    fun navigateToProductDetails(navigator: Navigator, id: String)
}