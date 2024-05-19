package com.rendo.app.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.rendo.feature.profile.ui.ProfileScreen
import org.jetbrains.compose.resources.stringResource
import rendo.composeapp.generated.resources.Res
import rendo.composeapp.generated.resources.profile

object ProfileTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Person)
            val title = stringResource(Res.string.profile)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(ProfileScreen())
    }
}