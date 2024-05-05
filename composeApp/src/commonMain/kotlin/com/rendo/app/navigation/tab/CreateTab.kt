package com.rendo.app.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.rendo.feature.create.rent.ui.CreateRentScreen

object CreateTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.AddCircle)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Create",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(CreateRentScreen())
    }
}