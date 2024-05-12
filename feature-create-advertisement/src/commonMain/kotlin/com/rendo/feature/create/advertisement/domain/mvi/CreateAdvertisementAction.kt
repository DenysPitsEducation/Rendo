package com.rendo.feature.create.advertisement.domain.mvi

internal sealed class CreateAdvertisementAction {
    data object Init : CreateAdvertisementAction()
}
