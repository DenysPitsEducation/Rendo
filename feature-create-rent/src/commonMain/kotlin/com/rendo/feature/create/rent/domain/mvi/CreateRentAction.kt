package com.rendo.feature.create.rent.domain.mvi

internal sealed class CreateRentAction {
    data object Init : CreateRentAction()
}
