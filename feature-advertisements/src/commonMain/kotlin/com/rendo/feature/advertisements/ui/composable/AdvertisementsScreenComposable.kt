package com.rendo.feature.advertisements.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.advertisements.di.AdvertisementsRouter
import com.rendo.feature.advertisements.domain.mvi.AdvertisementsLabel
import com.rendo.feature.advertisements.ui.AdvertisementsScreenModel
import com.rendo.feature.advertisements.ui.OnUserInteraction
import com.rendo.feature.advertisements.ui.mapper.AdvertisementsUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun AdvertisementsScreenComposable(screenModel: AdvertisementsScreenModel) {
    val router: AdvertisementsRouter = koinInject()
    val stateFlow by screenModel.store.stateFlow.collectAsState()
    val rentsMapper: AdvertisementsUiMapper = koinInject()
    val advertisementsUiModel = rentsMapper.mapToUiModel(stateFlow.advertisements)
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navigator.pop() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
            )
        }) { paddingValues ->
        AdvertisementsContentComposable(
            uiModel = advertisementsUiModel,
            onUserInteraction = onUserInteraction,
            modifier = Modifier.padding(paddingValues),
        )
    }

    LabelLaunchedEffect(screenModel.store.labels) {
        when (it) {
            is AdvertisementsLabel.OpenProductDetails -> {
                router.navigateToProductDetails(navigator, it.id)
            }
        }
    }
}
