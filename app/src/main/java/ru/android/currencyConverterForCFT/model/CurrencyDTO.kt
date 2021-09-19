package ru.android.currencyConverterForCFT.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyDTO(
    val CharCode: String,
    val Name: String,
    val Value: Double
): Parcelable