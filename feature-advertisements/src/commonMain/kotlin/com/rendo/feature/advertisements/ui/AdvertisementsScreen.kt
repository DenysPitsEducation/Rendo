package com.rendo.feature.advertisements.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.advertisements.ui.composable.AdvertisementsScreenComposable
import org.koin.core.parameter.parametersOf

class AdvertisementsScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<AdvertisementsScreenModel> { parametersOf() }
        AdvertisementsScreenComposable(screenModel)
    }
}