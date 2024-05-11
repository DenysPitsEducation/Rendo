package com.rendo.feature.rents.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.feature.rents.domain.model.DropdownAction
import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.domain.usecase.GetRentsUseCase

internal class RentsExecutor(
    private val getRentsUseCase: GetRentsUseCase,
) : CoroutineExecutor<RentsIntent, RentsAction, RentsState, RentsMessage, RentsLabel>() {

    override fun executeAction(action: RentsAction) = when (action) {
        is RentsAction.Init -> {
            val rents = getRentsUseCase.invoke()
            dispatch(RentsMessage.RentsUpdated(rents))
        }

        is RentsAction.FavoriteStateChanged -> {}
    }

    override fun executeIntent(intent: RentsIntent) = when (intent) {
        is RentsIntent.RentClicked -> onRentClicked(intent)
        is RentsIntent.AcceptButtonClicked -> onAcceptButtonClicked(intent)
        is RentsIntent.RejectButtonClicked -> onRejectButtonClicked(intent)
        is RentsIntent.DialNumberButtonClicked -> onDialNumberButtonClicked(intent)
        is RentsIntent.DropdownItemClicked -> onDropdownItemClicked(intent)
    }

    private fun onRentClicked(intent: RentsIntent.RentClicked) {
        val rent = state().rents.firstOrNull { it.id == intent.id } ?: return
        publish(RentsLabel.OpenProductDetails(rent.productId))
    }

    private fun onAcceptButtonClicked(intent: RentsIntent.AcceptButtonClicked) {
        val rent = state().rents.firstOrNull { it.id == intent.id } ?: return
        val rentUpdated = rent.copy(status = RentDomainModel.Status.ACCEPTED)
        dispatch(RentsMessage.RentUpdated(rentUpdated))
    }

    private fun onRejectButtonClicked(intent: RentsIntent.RejectButtonClicked) {
        val rent = state().rents.firstOrNull { it.id == intent.id } ?: return
        val rentUpdated = rent.copy(status = RentDomainModel.Status.REJECTED)
        dispatch(RentsMessage.RentUpdated(rentUpdated))
    }

    private fun onDialNumberButtonClicked(intent: RentsIntent.DialNumberButtonClicked) {
        val rent = state().rents.firstOrNull { it.id == intent.id } ?: return
        publish(RentsLabel.DialNumber(rent.phoneNumber))
    }

    private fun onDropdownItemClicked(intent: RentsIntent.DropdownItemClicked) {
        when (intent.action) {
            DropdownAction.CANCEL_RENT -> onCancelRentSelected(intent.id)
            DropdownAction.DELETE_RENT -> onDeleteRentSelected(intent.id)
        }
    }

    private fun onCancelRentSelected(id: String) {
        val rent = state().rents.firstOrNull { it.id == id } ?: return
        val rentUpdated = rent.copy(status = RentDomainModel.Status.CANCELLED)
        dispatch(RentsMessage.RentUpdated(rentUpdated))
    }

    private fun onDeleteRentSelected(id: String) {
        val rentsUpdated = state().rents.filter { it.id != id }
        dispatch(RentsMessage.RentsUpdated(rentsUpdated))
    }
}
