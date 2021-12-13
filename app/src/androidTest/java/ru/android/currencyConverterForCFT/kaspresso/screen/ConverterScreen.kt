package ru.android.currencyConverterForCFT.kaspresso.screen

import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import ru.android.currencyConverterForCFT.R

object ConverterScreen: Screen<ConverterScreen>() {

    val rublesInput = KEditText { withId(R.id.et_rubles) }
    val currencyInput = KEditText { withId(R.id.et_currency) }
    val charCodeRubles = KTextView { withId(R.id.tv_char_code_rubles) }
    val charCodeCurrency = KTextView { withId(R.id.tv_char_code_currency) }
    val rate = KTextView { withId(R.id.tv_currency_rate) }
}