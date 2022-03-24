package ru.android.currencyConverterForCFT.kaspresso.screen

import android.view.View
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import ru.android.currencyConverterForCFT.R

object MainScreen: Screen<MainScreen>() {

    val currenciesList = KRecyclerView(
        builder = { withId(R.id.recycler_view_currencies) },
        itemTypeBuilder = { itemType(::CurrencyItem) }
    )

    class CurrencyItem(parent: Matcher<View>) : KRecyclerItem<CurrencyItem>(parent) {
        val name = KTextView(parent) { withId(R.id.tv_item_currency_name) }
        val charCode = KTextView(parent) { withId(R.id.tv_item_currency_char_code) }
    }
}