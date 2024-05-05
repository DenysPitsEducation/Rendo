package com.rendo.feature.create.rent.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.create.rent.ui.composable.CreateRentScreenComposable
import org.koin.core.parameter.parametersOf

class CreateRentScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<CreateRentScreenModel> { parametersOf() }
        CreateRentScreenComposable(screenModel)
    }
}