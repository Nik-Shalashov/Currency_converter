package ru.android.currencyConverterForCFT.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrenciesListDTO(
    val Valute: ValuteList
): Parcelable

@Parcelize
data class ValuteList(
    val AUD: CurrencyDTO,
    val AZN: CurrencyDTO,
    val GBP: CurrencyDTO,
    val AMD: CurrencyDTO,
    val BYN: CurrencyDTO,
    val BGN: CurrencyDTO,
    val BRL: CurrencyDTO,
    val HUF: CurrencyDTO,
    val HKD: CurrencyDTO,
    val DKK: CurrencyDTO,
    val USD: CurrencyDTO,
    val EUR: CurrencyDTO,
    val INR: CurrencyDTO,
    val KZT: CurrencyDTO,
    val CAD: CurrencyDTO,
    val KGS: CurrencyDTO,
    val CNY: CurrencyDTO,
    val MDL: CurrencyDTO,
    val NOK: CurrencyDTO,
    val PLN: CurrencyDTO,
    val RON: CurrencyDTO,
    val XDR: CurrencyDTO,
    val SGD: CurrencyDTO,
    val TJS: CurrencyDTO,
    val TRY: CurrencyDTO,
    val TMT: CurrencyDTO,
    val UZS: CurrencyDTO,
    val UAH: CurrencyDTO,
    val CZK: CurrencyDTO,
    val SEK: CurrencyDTO,
    val CHF: CurrencyDTO,
    val ZAR: CurrencyDTO,
    val KRW: CurrencyDTO,
    val JPY: CurrencyDTO,
): Parcelable