package com.rendo.feature.create.advertisement.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.utils.toDoubleOrNullWithCleanup
import com.rendo.feature.create.advertisement.domain.model.InputDomainModel
import com.rendo.feature.create.advertisement.domain.model.InputType
import com.rendo.feature.create.advertisement.domain.model.AdvertisementDomainModel
import com.rendo.feature.create.advertisement.domain.usecase.CreateAdvertisementUseCase

internal class CreateAdvertisementExecutor(
    private val createAdvertisementUseCase: CreateAdvertisementUseCase,
) : CoroutineExecutor<CreateAdvertisementIntent, CreateAdvertisementAction, CreateAdvertisementState, CreateAdvertisementMessage, CreateAdvertisementLabel>() {

    override fun executeAction(action: CreateAdvertisementAction) = when (action) {
        is CreateAdvertisementAction.Init -> {
            // TODO Pits: Do nothing?
        }
    }

    override fun executeIntent(intent: CreateAdvertisementIntent) = when (intent) {
        is CreateAdvertisementIntent.InputChanged -> onInputChanged(intent)
        is CreateAdvertisementIntent.CreateAdvertisementButtonClicked -> onCreateAdvertisementButtonClicked()
    }

    private fun onInputChanged(intent: CreateAdvertisementIntent.InputChanged) {
        val text = if (intent.type == InputType.OWNER_PHONE_NUMBER) {
            intent.input.take(SHORT_PHONE_NUMBER_LENGTH)
        } else {
            intent.input
        }
        dispatch(CreateAdvertisementMessage.InputUpdated(InputDomainModel(text, null), intent.type))
    }

    private fun onCreateAdvertisementButtonClicked() {
        val state = state()

        if (validateFields(
                productName = state.productName,
                productDescription = state.productDescription,
                productPrice = state.productPrice,
                ownerName = state.ownerName,
                ownerPhoneNumber = state.ownerPhoneNumber
            )
        ) {
            createAdvertisementUseCase.invoke(
                AdvertisementDomainModel(
                    productName = state.productName.text,
                    productDescription = state.productDescription.text,
                    productPrice = getProductPriceDouble(state.productPrice)!!,
                    ownerName = state.ownerName.text,
                    ownerPhoneNumber = "380" + state.ownerPhoneNumber.text,
                )
            )
        }
    }

    private fun validateFields(
        productName: InputDomainModel,
        productDescription: InputDomainModel,
        productPrice: InputDomainModel,
        ownerName: InputDomainModel,
        ownerPhoneNumber: InputDomainModel
    ): Boolean {
        var isValid = true
        val blankFieldError = "Field must be filled"

        if (productName.text.isBlank()) {
            val fieldUpdated = productName.copy(errorText = blankFieldError)
            dispatch(CreateAdvertisementMessage.InputUpdated(fieldUpdated, InputType.PRODUCT_NAME))
            isValid = false
        }

        if (productDescription.text.isBlank()) {
            val fieldUpdated = productDescription.copy(errorText = blankFieldError)
            dispatch(CreateAdvertisementMessage.InputUpdated(fieldUpdated, InputType.PRODUCT_DESCRIPTION))
            isValid = false
        }

        val productPriceText = productPrice.text
        val priceErrorText = when {
            productPriceText.isBlank() -> blankFieldError
            getProductPriceDouble(productPrice) == null -> "Invalid number"
            else -> null
        }
        if (priceErrorText != null) {
            val fieldUpdated = productPrice.copy(errorText = priceErrorText)
            dispatch(CreateAdvertisementMessage.InputUpdated(fieldUpdated, InputType.PRODUCT_PRICE))
            isValid = false
        }

        if (ownerName.text.isBlank()) {
            val fieldUpdated = ownerName.copy(errorText = blankFieldError)
            dispatch(CreateAdvertisementMessage.InputUpdated(fieldUpdated, InputType.OWNER_NAME))
            isValid = false
        }

        if (ownerPhoneNumber.text.length != SHORT_PHONE_NUMBER_LENGTH) {
            val fieldUpdated = ownerPhoneNumber.copy(errorText = blankFieldError)
            dispatch(CreateAdvertisementMessage.InputUpdated(fieldUpdated, InputType.OWNER_PHONE_NUMBER))
            isValid = false
        }

        return isValid
    }

    private fun getProductPriceDouble(productPrice: InputDomainModel) =
        productPrice.text.toDoubleOrNullWithCleanup()

    companion object {
        const val SHORT_PHONE_NUMBER_LENGTH = 9
    }
}