package com.rendo.feature.advertisements.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.feature.advertisements.domain.usecase.DeleteAdvertisementUseCase
import com.rendo.feature.advertisements.domain.usecase.GetAdvertisementsUseCase
import kotlinx.coroutines.launch

internal class AdvertisementsExecutor(
    private val deleteAdvertisementUseCase: DeleteAdvertisementUseCase,
    private val getAdvertisementsUseCase: GetAdvertisementsUseCase,
) : CoroutineExecutor<AdvertisementsIntent, AdvertisementsAction, AdvertisementsState, AdvertisementsMessage, AdvertisementsLabel>() {

    override fun executeAction(action: AdvertisementsAction) = when (action) {
        is AdvertisementsAction.Init -> onInit()
    }

    private fun onInit() {
        scope.launch {
            val rents = getAdvertisementsUseCase.invoke()
            dispatch(AdvertisementsMessage.AdvertisementsUpdated(rents))
        }
    }

    override fun executeIntent(intent: AdvertisementsIntent) = when (intent) {
        is AdvertisementsIntent.AdvertisementClicked -> onRentClicked(intent)
        is AdvertisementsIntent.DeleteButtonClicked -> onDeleteButtonClicked(intent)
    }

    private fun onRentClicked(intent: AdvertisementsIntent.AdvertisementClicked) {
        val advertisement = state().advertisements.firstOrNull { it.productId == intent.id } ?: return
        publish(AdvertisementsLabel.OpenProductDetails(advertisement.productId))
    }

    private fun onDeleteButtonClicked(intent: AdvertisementsIntent.DeleteButtonClicked) {
        scope.launch {
            deleteAdvertisementUseCase.invoke(intent.id).onSuccess {
                val advertisementsUpdated = state().advertisements.filter { it.productId != intent.id }
                dispatch(AdvertisementsMessage.AdvertisementsUpdated(advertisementsUpdated))
            }
        }
    }
}
