package com.rendo.feature.create.rent.domain.mvi

sealed class CreateRentAction {
    data object Init : CreateRentAction()
}
