package com.rendo.feature.profile.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.rendo.feature.profile.ui.composable.ProfileScreenComposable
import org.koin.core.parameter.parametersOf

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<ProfileScreenModel> { parametersOf() }
        ProfileScreenComposable(screenModel)
    }
}