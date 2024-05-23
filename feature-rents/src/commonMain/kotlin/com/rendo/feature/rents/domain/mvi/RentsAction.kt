package com.rendo.feature.rents.domain.mvi

internal sealed class RentsAction {
    data class AuthorizationStateUpdated(val isAuthorized: Boolean) : RentsAction()
}
