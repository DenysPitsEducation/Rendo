package com.rendo.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun <T> LabelLaunchedEffect(
    labelFlow: Flow<T>,
    labelHandler: suspend CoroutineScope.(T) -> Unit,
) {
    LaunchedEffect(Unit) {
        // TODO: lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
        labelFlow.collect { label ->
            launch {
                labelHandler.invoke(this, label)
            }
        }
    }
}