package com.rendo.feature.create.rent.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.utils.toDoubleOrNullWithCleanup
import com.rendo.feature.create.rent.domain.model.InputDomainModel
import com.rendo.feature.create.rent.domain.model.InputType
import com.rendo.feature.create.rent.domain.model.RentDomainModel
import com.rendo.feature.create.rent.domain.usecase.CreateRentUseCase

internal class CreateRentExecutor(
    private val createRentUseCase: CreateRentUseCase,
) : CoroutineExecutor<CreateRentIntent, CreateRentAction, CreateRentState, CreateRentMessage, CreateRentLabel>() {

    override fun executeAction(action: CreateRentAction) = when (action) {
        is CreateRentAction.Init -> {
            // TODO Pits: Do nothing?
        }
    }

    override fun executeIntent(intent: CreateRentIntent) = when (intent) {
        is CreateRentIntent.InputChanged -> onInputChanged(intent)
        is CreateRentIntent.CreateRentButtonClicked -> onCreateRentButtonClicked()
    }

    private fun onInputChanged(intent: CreateRentIntent.InputChanged) {
        val text = if (intent.type == InputType.OWNER_PHONE_NUMBER) {
            intent.input.take(SHORT_PHONE_NUMBER_LENGTH)
        } else {
            intent.input
        }
        dispatch(CreateRentMessage.InputUpdated(InputDomainModel(text, null), intent.type))
    }

    private fun onCreateRentButtonClicked() {
        val state = state()

        if (validateFields(
                productName = state.productName,
                productDescription = state.productDescription,
                productPrice = state.productPrice,
                ownerName = state.ownerName,
                ownerPhoneNumber = state.ownerPhoneNumber
            )
        ) {
            createRentUseCase.invoke(
                RentDomainModel(
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
            dispatch(CreateRentMessage.InputUpdated(fieldUpdated, InputType.PRODUCT_NAME))
            isValid = false
        }

        if (productDescription.text.isBlank()) {
            val fieldUpdated = productDescription.copy(errorText = blankFieldError)
            dispatch(CreateRentMessage.InputUpdated(fieldUpdated, InputType.PRODUCT_DESCRIPTION))
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
            dispatch(CreateRentMessage.InputUpdated(fieldUpdated, InputType.PRODUCT_PRICE))
            isValid = false
        }

        if (ownerName.text.isBlank()) {
            val fieldUpdated = ownerName.copy(errorText = blankFieldError)
            dispatch(CreateRentMessage.InputUpdated(fieldUpdated, InputType.OWNER_NAME))
            isValid = false
        }

        if (ownerPhoneNumber.text.length != SHORT_PHONE_NUMBER_LENGTH) {
            val fieldUpdated = ownerPhoneNumber.copy(errorText = blankFieldError)
            dispatch(CreateRentMessage.InputUpdated(fieldUpdated, InputType.OWNER_PHONE_NUMBER))
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
