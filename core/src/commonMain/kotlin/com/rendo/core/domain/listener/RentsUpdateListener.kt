package com.rendo.core.domain.listener

import kotlinx.coroutines.flow.MutableSharedFlow

class RentsUpdateListener {

    private val flow = MutableSharedFlow<Unit>()

    fun getUpdates() = flow

    suspend fun triggerUpdate() {
        flow.emit(Unit)
    }
}