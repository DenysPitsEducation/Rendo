package com.rendo.feature.product.details.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.favorites.domain.usecase.ChangeFavoriteStateUseCase
import com.rendo.core.utils.fromEpochMilliseconds
import com.rendo.feature.product.details.domain.mapper.toProductDomainModel
import com.rendo.feature.product.details.domain.usecase.GetProductDetailsUseCase
import com.rendo.feature.product.details.domain.usecase.RentProductUseCase
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil

internal class ProductDetailsExecutor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val rentProductUseCase: RentProductUseCase,
    private val changeFavoriteStateUseCase: ChangeFavoriteStateUseCase,
) : CoroutineExecutor<ProductDetailsIntent, ProductDetailsAction, ProductDetailsState, ProductDetailsMessage, ProductDetailsLabel>() {
    override fun executeAction(action: ProductDetailsAction) = when (action) {
        is ProductDetailsAction.Init -> onInit(action)
        is ProductDetailsAction.FavoriteStateChanged -> onFavoriteStateChanged(action)
    }

    private fun onInit(action: ProductDetailsAction.Init) {
        scope.launch {
            getProductDetailsUseCase.invoke(action.payload.id).onSuccess { product ->
                dispatch(ProductDetailsMessage.ProductUpdated(product))
            }
        }
    }

    private fun onFavoriteStateChanged(action: ProductDetailsAction.FavoriteStateChanged) {
        val product = state().product ?: return
        val productUpdated = product.copy(isInFavorites = action.isInFavorites)
        dispatch(ProductDetailsMessage.ProductUpdated(productUpdated))
    }

    override fun executeIntent(intent: ProductDetailsIntent) = when (intent) {
        is ProductDetailsIntent.FavoriteButtonClicked -> onFavoriteButtonClicked()
        is ProductDetailsIntent.RentButtonClicked -> onRentButtonClicked()
        is ProductDetailsIntent.ChangeDatesButtonClicked -> onChangeDatesButtonClicked()
        is ProductDetailsIntent.CallOwnerButtonClicked -> onCallOwnerButtonClicked()
        is ProductDetailsIntent.ChooseDateDialogButtonClicked -> onChooseDateDialogButtonClicked(
            intent
        )
    }

    private fun onFavoriteButtonClicked() {
        scope.launch {
            val product = state().product ?: return@launch
            changeFavoriteStateUseCase.invoke(product.toProductDomainModel())
        }
    }

    private fun onRentButtonClicked() {
        val product = state().product ?: return
        rentProductUseCase.invoke(product).onSuccess {
            publish(ProductDetailsLabel.ShowSuccessfulRentDialog)
        }
    }

    private fun onChangeDatesButtonClicked() {
        publish(ProductDetailsLabel.OpenDateRangePicker)
    }

    private fun onCallOwnerButtonClicked() {
        val product = state().product ?: return
        publish(ProductDetailsLabel.DialNumber(product.owner.phone))
    }

    private fun onChooseDateDialogButtonClicked(intent: ProductDetailsIntent.ChooseDateDialogButtonClicked) {
        val product = state().product ?: return
        val pickupDate = LocalDate.fromEpochMilliseconds(intent.pickupDateMillis)
        val returnDate = LocalDate.fromEpochMilliseconds(intent.returnDateMillis)
        val productUpdated = product.copy(
            pickupDate = pickupDate,
            returnDate = returnDate,
            totalPrice = product.price * (pickupDate.daysUntil(returnDate) + 1),
        )
        dispatch(ProductDetailsMessage.ProductUpdated(productUpdated))
    }
}