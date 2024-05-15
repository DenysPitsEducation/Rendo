package com.rendo.feature.rents.domain.mvi

internal sealed class RentsAction {
    data object Init : RentsAction()
    data class AuthorizationStateUpdated(val isAuthorized: Boolean) : RentsAction()
}
