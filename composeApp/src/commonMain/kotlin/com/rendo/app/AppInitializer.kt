package com.rendo.app

import com.rendo.core.buildConfig.BuildConfig
import com.rendo.core.buildConfig.isDebug
import com.rendo.core.favorites.domain.usecase.RefreshFavoriteProductsUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.crashlytics.crashlytics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class AppInitializer(
    private val refreshFavoriteProductsUseCase: RefreshFavoriteProductsUseCase,
) {
    fun initialize() {
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(!BuildConfig.isDebug)
        CoroutineScope(Dispatchers.IO).launch {
            if (Firebase.auth.currentUser == null) {
                Firebase.auth.signInAnonymously()
            }
            refreshFavoriteProductsUseCase.invoke()
        }
    }
}