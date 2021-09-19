package ru.android.currencyConverterForCFT.repository

import retrofit2.http.GET
import ru.android.currencyConverterForCFT.model.CurrenciesListDTO

interface CurrencyAPI {

    @GET("daily_json.js")
    fun getCurrencies(): retrofit2.Call<CurrenciesListDTO>
}