package com.rendo.app.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.rendo.feature.favorites.ui.FavoritesScreen
import org.jetbrains.compose.resources.stringResource
import rendo.composeapp.generated.resources.Res
import rendo.composeapp.generated.resources.favorites

object FavoriteTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Favorite)
            val title = stringResource(Res.string.favorites)

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
        Navigator(FavoritesScreen())
    }
}