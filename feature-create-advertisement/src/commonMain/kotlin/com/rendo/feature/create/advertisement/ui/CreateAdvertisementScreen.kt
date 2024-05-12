package com.rendo.feature.create.advertisement.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.create.advertisement.ui.composable.CreateAdvertisementScreenComposable
import org.koin.core.parameter.parametersOf

class CreateAdvertisementScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<CreateAdvertisementScreenModel> { parametersOf() }
        CreateAdvertisementScreenComposable(screenModel)
    }
}