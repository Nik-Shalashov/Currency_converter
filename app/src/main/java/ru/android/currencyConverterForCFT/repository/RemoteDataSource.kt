package ru.android.currencyConverterForCFT.repository

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.android.currencyConverterForCFT.model.CurrenciesListDTO

class RemoteDataSource {

    private val currencyAPI = Retrofit.Builder()
        .baseUrl("https://www.cbr-xml-daily.ru/")
        .client(
            OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(CurrencyAPI::class.java)

    fun getCurrenciesList(callback: Callback<CurrenciesListDTO>) {
        currencyAPI.getCurrencies().enqueue(callback)
    }
}