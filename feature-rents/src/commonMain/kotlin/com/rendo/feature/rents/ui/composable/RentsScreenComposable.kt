package com.rendo.feature.rents.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.dial.Dialer
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.rents.di.RentsRouter
import com.rendo.feature.rents.domain.mvi.RentsLabel
import com.rendo.feature.rents.ui.OnUserInteraction
import com.rendo.feature.rents.ui.RentsScreenModel
import com.rendo.feature.rents.ui.mapper.RentsUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RentsScreenComposable(screenModel: RentsScreenModel) {
    val router: RentsRouter = koinInject()
    val dialer: Dialer = koinInject()
    val stateFlow by screenModel.store.stateFlow.collectAsState()
    val rentsMapper: RentsUiMapper = koinInject()
    val rentsUiModel = rentsMapper.mapToUiModel(stateFlow.rents)
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }
    val navigator = LocalNavigator.currentOrThrow

    RentsContentComposable(
        rentsUiModel = rentsUiModel, onUserInteraction
    )

    LabelLaunchedEffect(screenModel.store.labels) {
        when (it) {
            is RentsLabel.OpenProductDetails -> {
                router.navigateToProductDetails(navigator, it.id)
            }

            is RentsLabel.DialNumber -> {
                dialer.makeCall(it.phoneNumber)
            }
        }
    }
}
