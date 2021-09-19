package ru.android.currencyConverterForCFT.viewModel

import ru.android.currencyConverterForCFT.model.CurrenciesListDTO

sealed class AppState {
    data class Success(val currencyData: CurrenciesListDTO): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
