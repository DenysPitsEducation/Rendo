package com.rendo.feature.favorites.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.favorites.ui.composable.FavoritesScreenComposable
import org.koin.core.parameter.parametersOf

class FavoritesScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<FavoritesScreenModel> { parametersOf() }
        FavoritesScreenComposable(screenModel)
    }
}