package com.rendo.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.rendo.app.navigation.tab.CreateTab
import com.rendo.app.navigation.tab.FavoriteTab
import com.rendo.app.navigation.tab.HomeTab
import com.rendo.app.navigation.tab.ProfileTab
import com.rendo.app.navigation.tab.RentsTab
import com.rendo.core.domain.model.UiMode
import com.rendo.core.domain.usecase.GetUiModeFlowUseCase
import com.rendo.core.theme.AppTheme
import com.rendo.core.theme.LocalSnackbarHostState
import com.rendo.core.theme.LocalThemeIsDark
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
internal fun App() = KoinContext {
    AppTheme {
        val getUiModeFlowUseCase: GetUiModeFlowUseCase = koinInject()
        var isDarkMode by LocalThemeIsDark.current
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        coroutineScope.launch {
            getUiModeFlowUseCase.invoke().collect { uiMode ->
                when (uiMode) {
                    UiMode.LIGHT -> isDarkMode = false
                    UiMode.DARK -> isDarkMode = true
                    UiMode.SYSTEM -> { /* do nothing */
                    }
                }
            }
        }
        CompositionLocalProvider(
            LocalSnackbarHostState provides snackbarHostState,
        ) {
            TabNavigator(
                HomeTab,
                tabDisposable = {
                    TabDisposable(
                        navigator = it,
                        tabs = listOf(HomeTab, FavoriteTab, ProfileTab)
                    )
                }
            ) {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier.consumeWindowInsets(innerPadding)
                                .padding(innerPadding)
                        ) {
                            CurrentTab()
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(HomeTab)
                            NavigationBarItem(FavoriteTab)
                            NavigationBarItem(CreateTab)
                            NavigationBarItem(RentsTab)
                            NavigationBarItem(ProfileTab)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun RowScope.NavigationBarItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },
        label = { Text(tab.options.title) },
    )
}
