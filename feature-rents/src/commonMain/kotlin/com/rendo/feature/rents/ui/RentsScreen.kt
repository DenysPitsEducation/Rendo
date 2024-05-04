package com.rendo.feature.rents.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.rents.ui.composable.RentsScreenComposable
import org.koin.core.parameter.parametersOf

class RentsScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<RentsScreenModel> { parametersOf() }
        RentsScreenComposable(screenModel)
    }
}