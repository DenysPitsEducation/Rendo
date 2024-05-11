package com.rendo.app.di

import cafe.adriel.voyager.navigator.Navigator
import com.rendo.app.AppInitializer
import com.rendo.feature.advertisements.di.AdvertisementsRouter
import com.rendo.feature.advertisements.ui.AdvertisementsScreen
import com.rendo.feature.favorites.di.FavoritesRouter
import com.rendo.feature.home.di.HomeRouter
import com.rendo.feature.product.details.domain.mvi.ProductDetailsPayload
import com.rendo.feature.product.details.ui.ProductDetailsScreen
import com.rendo.feature.profile.di.ProfileRouter
import com.rendo.feature.rents.di.RentsRouter
import org.koin.dsl.module

fun appModule() = module {
    factory {
        AppInitializer(favoritesRepository = get())
    }
    factory<HomeRouter> {
        object : HomeRouter {
            override fun navigateToProductDetails(navigator: Navigator, id: String) {
                navigator.push(ProductDetailsScreen(ProductDetailsPayload(id)))
            }
        }
    }
    factory<FavoritesRouter> {
        object : FavoritesRouter {
            override fun navigateToProductDetails(navigator: Navigator, id: String) {
                navigator.push(ProductDetailsScreen(ProductDetailsPayload(id)))
            }
        }
    }
    factory<RentsRouter> {
        object : RentsRouter {
            override fun navigateToProductDetails(navigator: Navigator, id: String) {
                navigator.push(ProductDetailsScreen(ProductDetailsPayload(id)))
            }
        }
    }
    factory<ProfileRouter> {
        object : ProfileRouter {
            override fun navigateToAdvertisements(navigator: Navigator) {
                navigator.push(AdvertisementsScreen())
            }
        }
    }
    factory<AdvertisementsRouter> {
        object : AdvertisementsRouter {
            override fun navigateToProductDetails(navigator: Navigator, id: String) {
                navigator.push(ProductDetailsScreen(ProductDetailsPayload(id)))
            }
        }
    }
}