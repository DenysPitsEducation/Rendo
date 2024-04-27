package com.rendo.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.rendo.app.navigation.tab.CreateTab
import com.rendo.app.navigation.tab.FavoriteTab
import com.rendo.feature.home.ui.HomeTab
import com.rendo.app.navigation.tab.MyRentsTab
import com.rendo.app.navigation.tab.ProfileTab
import com.rendo.core.theme.AppTheme
import com.rendo.feature.home.di.featureHomeModule
import org.koin.compose.KoinApplication

@Composable
internal fun App() = KoinApplication(application = {
    modules(
        featureHomeModule(),
    )
}) {
    AppTheme {
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
                content = { innerPadding ->
                    Box(
                        modifier = Modifier.consumeWindowInsets(innerPadding).padding(innerPadding)
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(HomeTab)
                        NavigationBarItem(FavoriteTab)
                        NavigationBarItem(CreateTab)
                        NavigationBarItem(MyRentsTab)
                        NavigationBarItem(ProfileTab)
                    }
                },
            )
        }
    }
}


/*Column(
    modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.safeDrawing)
        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stringResource(Res.string.cyclone),
        fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
        style = MaterialTheme.typography.displayLarge
    )

    var isAnimate by remember { mutableStateOf(false) }
    val transition = rememberInfiniteTransition()
    val rotate by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )

    Image(
        modifier = Modifier
            .size(250.dp)
            .padding(16.dp)
            .run { if (isAnimate) rotate(rotate) else this },
        imageVector = vectorResource(Res.drawable.ic_cyclone),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        contentDescription = null
    )

    ElevatedButton(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .widthIn(min = 200.dp),
        onClick = { isAnimate = !isAnimate },
        content = {
            Icon(vectorResource(Res.drawable.ic_rotate_right), contentDescription = null)
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                stringResource(if (isAnimate) Res.string.stop else Res.string.run)
            )
        }
    )

    var isDark by LocalThemeIsDark.current
    val icon = remember(isDark) {
        if (isDark) Res.drawable.ic_light_mode
        else Res.drawable.ic_dark_mode
    }

    ElevatedButton(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
        onClick = { isDark = !isDark },
        content = {
            Icon(vectorResource(icon), contentDescription = null)
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(Res.string.theme))
        }
    )

    TextButton(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
        onClick = { openUrl("https://github.com/terrakok") },
    ) {
        Text(stringResource(Res.string.open_github))
    }
}*/

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

internal expect fun openUrl(url: String?)
