package com.rendo.feature.create.advertisement.domain.mvi

internal sealed class CreateAdvertisementAction {
    data class AuthorizationStateUpdated(val isAuthorized: Boolean) : CreateAdvertisementAction()
}
