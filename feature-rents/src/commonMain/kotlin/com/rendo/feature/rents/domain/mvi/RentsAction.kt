package com.rendo.feature.rents.domain.mvi

internal sealed class RentsAction {
    data object Init : RentsAction()
}
