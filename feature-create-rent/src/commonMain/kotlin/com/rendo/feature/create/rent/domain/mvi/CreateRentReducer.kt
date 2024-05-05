package com.rendo.feature.create.rent.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer
import com.rendo.feature.create.rent.domain.model.InputType

internal class CreateRentReducer : Reducer<CreateRentState, CreateRentMessage> {
    override fun CreateRentState.reduce(
        msg: CreateRentMessage,
    ): CreateRentState = when (msg) {
        is CreateRentMessage.InputUpdated -> {
            when (msg.type) {
                InputType.PRODUCT_NAME -> copy(productName = msg.input)
                InputType.PRODUCT_DESCRIPTION -> copy(productDescription = msg.input)
                InputType.PRODUCT_PRICE -> copy(productPrice = msg.input)
                InputType.OWNER_NAME -> copy(ownerName = msg.input)
                InputType.OWNER_PHONE_NUMBER -> copy(ownerPhoneNumber = msg.input)
            }
        }
    }
}