package ru.android.currencyConverterForCFT.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import ru.android.currencyConverterForCFT.model.CurrenciesListDTO
import ru.android.currencyConverterForCFT.repository.CurrenciesRepository
import ru.android.currencyConverterForCFT.repository.CurrenciesRepositoryImpl
import ru.android.currencyConverterForCFT.repository.RemoteDataSource

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class MainViewModel(
    val currenciesLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val currenciesRepositoryImpl: CurrenciesRepository = CurrenciesRepositoryImpl(
        RemoteDataSource()
    )
): ViewModel() {

    fun getCurrenciesFromRemoteSource() {
        currenciesLiveData.value = AppState.Loading
        currenciesRepositoryImpl.getCurrenciesFromServer(callback)
    }

    private val callback = object : retrofit2.Callback<CurrenciesListDTO> {
        override fun onResponse(
            call: Call<CurrenciesListDTO>,
            response: Response<CurrenciesListDTO>
        ) {
            val serverResponse: CurrenciesListDTO? = response.body()
            currenciesLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<CurrenciesListDTO>, t: Throwable) {
            currenciesLiveData.postValue(AppState.Error(Throwable(t.message?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: CurrenciesListDTO): AppState {
            return if (serverResponse.equals(null)) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(serverResponse)
            }
        }

    }
}