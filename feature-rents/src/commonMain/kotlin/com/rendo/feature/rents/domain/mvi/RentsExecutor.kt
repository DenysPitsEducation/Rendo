package com.rendo.feature.rents.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.feature.rents.domain.model.DropdownAction
import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.domain.usecase.AcceptRentUseCase
import com.rendo.feature.rents.domain.usecase.CancelRentUseCase
import com.rendo.feature.rents.domain.usecase.DeleteRentUseCase
import com.rendo.feature.rents.domain.usecase.GetRentsUseCase
import com.rendo.feature.rents.domain.usecase.RejectRentUseCase
import kotlinx.coroutines.launch

internal class RentsExecutor(
    private val getRentsUseCase: GetRentsUseCase,
    private val acceptRentUseCase: AcceptRentUseCase,
    private val rejectRentUseCase: RejectRentUseCase,
    private val cancelRentUseCase: CancelRentUseCase,
    private val deleteRentUseCase: DeleteRentUseCase,
) : CoroutineExecutor<RentsIntent, RentsAction, RentsState, RentsMessage, RentsLabel>() {

    override fun executeAction(action: RentsAction) = when (action) {
        is RentsAction.AuthorizationStateUpdated -> onAuthorizationStateUpdated()
    }

    private fun onAuthorizationStateUpdated() {
        refreshRents()
    }

    private fun refreshRents() {
        scope.launch {
            getRentsUseCase.invoke().onSuccess { rents ->
                dispatch(RentsMessage.RentsUpdated(rents))
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    override fun executeIntent(intent: RentsIntent) = when (intent) {
        is RentsIntent.ScreenOpened -> onScreenOpened()
        is RentsIntent.RentClicked -> onRentClicked(intent)
        is RentsIntent.AcceptButtonClicked -> onAcceptButtonClicked(intent)
        is RentsIntent.RejectButtonClicked -> onRejectButtonClicked(intent)
        is RentsIntent.DialNumberButtonClicked -> onDialNumberButtonClicked(intent)
        is RentsIntent.DropdownItemClicked -> onDropdownItemClicked(intent)
    }

    private fun onScreenOpened() {
        refreshRents()
    }

    private fun onRentClicked(intent: RentsIntent.RentClicked) {
        val rent = state().rents.firstOrNull { it.id == intent.id } ?: return
        publish(RentsLabel.OpenProductDetails(rent.productId))
    }

    private fun onAcceptButtonClicked(intent: RentsIntent.AcceptButtonClicked) {
        scope.launch {
            val rent = state().rents.firstOrNull { it.id == intent.id } ?: return@launch
            acceptRentUseCase.invoke(rent).onSuccess {
                val rentUpdated = rent.copy(status = RentDomainModel.Status.ACCEPTED)
                dispatch(RentsMessage.RentUpdated(rentUpdated))
            }
        }
    }

    private fun onRejectButtonClicked(intent: RentsIntent.RejectButtonClicked) {
        scope.launch {
            val rent = state().rents.firstOrNull { it.id == intent.id } ?: return@launch
            rejectRentUseCase.invoke(rent).onSuccess {
                val rentUpdated = rent.copy(status = RentDomainModel.Status.REJECTED)
                dispatch(RentsMessage.RentUpdated(rentUpdated))
            }
        }
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
        scope.launch {
            val rent = state().rents.firstOrNull { it.id == id } ?: return@launch
            cancelRentUseCase.invoke(rent).onSuccess {
                val rentUpdated = rent.copy(status = RentDomainModel.Status.CANCELLED)
                dispatch(RentsMessage.RentUpdated(rentUpdated))
            }
        }
    }

    private fun onDeleteRentSelected(id: String) {
        scope.launch {
            val rent = state().rents.firstOrNull { it.id == id } ?: return@launch
            deleteRentUseCase.invoke(rent).onSuccess {
                val rentsUpdated = state().rents.filter { it.id != id }
                dispatch(RentsMessage.RentsUpdated(rentsUpdated))
            }
        }
    }
}
