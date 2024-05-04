package com.rendo.feature.rents.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer

internal class RentsReducer : Reducer<RentsState, RentsMessage> {
    override fun RentsState.reduce(
        msg: RentsMessage,
    ): RentsState = when (msg) {
        is RentsMessage.RentsUpdated -> copy(rents = msg.rents)
        is RentsMessage.RentUpdated -> {
            val rentsUpdated = rents.map { rent ->
                if (msg.rent.id == rent.id) {
                    msg.rent
                } else {
                    rent
                }
            }
            copy(rents = rentsUpdated)
        }
    }
}