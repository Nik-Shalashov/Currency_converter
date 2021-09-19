package ru.android.currencyConverterForCFT.repository

import ru.android.currencyConverterForCFT.model.CurrenciesListDTO

interface CurrenciesRepository {

    fun getCurrenciesFromServer(
        callback: retrofit2.Callback<CurrenciesListDTO>
    )
}