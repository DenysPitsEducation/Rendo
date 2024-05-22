package com.rendo.feature.create.advertisement.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer
import com.rendo.feature.create.advertisement.domain.model.InputType

internal class CreateAdvertisementReducer : Reducer<CreateAdvertisementState, CreateAdvertisementMessage> {
    override fun CreateAdvertisementState.reduce(
        msg: CreateAdvertisementMessage,
    ): CreateAdvertisementState = when (msg) {
        is CreateAdvertisementMessage.StateUpdated -> {
            msg.state
        }
        is CreateAdvertisementMessage.AuthorizationStateUpdated -> {
            copy(isAuthorized = msg.isAuthorized)
        }
        is CreateAdvertisementMessage.ImagesUpdated -> {
            copy(images = msg.images)
        }
        is CreateAdvertisementMessage.InputUpdated -> {
            when (msg.type) {
                InputType.PRODUCT_NAME -> copy(productName = msg.input)
                InputType.PRODUCT_DESCRIPTION -> copy(productDescription = msg.input)
                InputType.PRODUCT_PRICE -> copy(productPrice = msg.input)
                InputType.PRODUCT_LOCATION -> copy(productLocation = msg.input)
                InputType.OWNER_NAME -> copy(ownerName = msg.input)
                InputType.OWNER_PHONE_NUMBER -> copy(ownerPhoneNumber = msg.input)
            }
        }
    }
}