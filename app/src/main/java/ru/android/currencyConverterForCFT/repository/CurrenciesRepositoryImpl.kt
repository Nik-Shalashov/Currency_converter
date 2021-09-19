package ru.android.currencyConverterForCFT.repository

import retrofit2.Callback
import ru.android.currencyConverterForCFT.model.CurrenciesListDTO

class CurrenciesRepositoryImpl(private val remoteDataSource: RemoteDataSource): CurrenciesRepository {
    override fun getCurrenciesFromServer(callback: Callback<CurrenciesListDTO>) {
        remoteDataSource.getCurrenciesList(callback)
    }
}