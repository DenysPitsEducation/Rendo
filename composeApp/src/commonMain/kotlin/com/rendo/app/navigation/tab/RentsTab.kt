package com.rendo.app.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.rendo.feature.home.ui.HomeScreen
import com.rendo.feature.rents.ui.RentsScreen

object RentsTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.AutoMirrored.Default.List)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "My rents",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(RentsScreen())
    }
}