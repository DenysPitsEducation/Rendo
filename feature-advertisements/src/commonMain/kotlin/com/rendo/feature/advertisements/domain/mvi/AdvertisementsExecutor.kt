package com.rendo.feature.advertisements.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.feature.advertisements.domain.usecase.GetAdvertisementsUseCase

internal class AdvertisementsExecutor(
    private val getAdvertisementsUseCase: GetAdvertisementsUseCase,
) : CoroutineExecutor<AdvertisementsIntent, AdvertisementsAction, AdvertisementsState, AdvertisementsMessage, AdvertisementsLabel>() {

    override fun executeAction(action: AdvertisementsAction) = when (action) {
        is AdvertisementsAction.Init -> {
            val rents = getAdvertisementsUseCase.invoke()
            dispatch(AdvertisementsMessage.AdvertisementsUpdated(rents))
        }
    }

    override fun executeIntent(intent: AdvertisementsIntent) = when (intent) {
        is AdvertisementsIntent.AdvertisementClicked -> onRentClicked(intent)
        is AdvertisementsIntent.DeleteButtonClicked -> onDeleteButtonClicked(intent)
    }

    private fun onRentClicked(intent: AdvertisementsIntent.AdvertisementClicked) {
        val advertisement = state().advertisements.firstOrNull { it.id == intent.id } ?: return
        publish(AdvertisementsLabel.OpenProductDetails(advertisement.productId))
    }

    private fun onDeleteButtonClicked(intent: AdvertisementsIntent.DeleteButtonClicked) {
        val advertisementsUpdated = state().advertisements.filter { it.id != intent.id }
        dispatch(AdvertisementsMessage.AdvertisementsUpdated(advertisementsUpdated))
    }
}
