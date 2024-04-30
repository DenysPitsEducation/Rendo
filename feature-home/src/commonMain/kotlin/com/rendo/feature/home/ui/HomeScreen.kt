package com.rendo.feature.home.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.home.ui.composable.HomeScreenComposable
import org.koin.core.parameter.parametersOf

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeScreenModel> { parametersOf() }
        HomeScreenComposable(screenModel)
    }
}