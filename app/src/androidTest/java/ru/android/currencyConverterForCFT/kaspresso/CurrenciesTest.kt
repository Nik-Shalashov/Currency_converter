package ru.android.currencyConverterForCFT.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.android.currencyConverterForCFT.TestCase
import ru.android.currencyConverterForCFT.kaspresso.screen.ConverterScreen
import ru.android.currencyConverterForCFT.kaspresso.screen.MainScreen
import ru.android.currencyConverterForCFT.view.MainActivity

@RunWith(AndroidJUnit4::class)
class CurrenciesTest: KTestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @TestCase(name = "Case 1", description = "Загрузка списка валют")
    fun checkCurrenciesList() {
        run {
            step("Переход к fragment_main") {

            }

            step("Получение списка валют") {
                checkCurrencies(
                    Currency(name = "Евро", charCode = "EUR"),
                    Currency(name = "Доллар США", charCode = "USD"),
                    Currency(name = "Китайский юань", charCode = "CNY"),
                    Currency(name = "Индийских рупий", charCode = "INR"),
                    Currency(name = "Канадский доллар", charCode = "CAD")
                )
            }
        }
    }

    @TestCase(name = "Case 2", description = "Проверка UI конвертера")
    fun checkConverterUI(vararg currency: Currency) {
        run {
            step("Переход к fragment_converter") {
                openConverter(Currency(name = "Доллар США", charCode = "USD"))
            }

            step("Проверка UI конвертера") {
                ConverterScreen {
                    rublesInput {
                        typeText("1000")
                    }
                    currencyInput{
                        hasText("${1000/70}")
                    }
                    charCodeRubles{
                        hasText("RUB")
                    }
                    charCodeCurrency{
                        hasText("USD")
                    }
                    rate{
                        hasText(70)
                    }
                }
            }
        }
    }

    class Currency(val name: String, val charCode: String)

    private fun checkCurrencies(vararg currencies: Currency) {
        currencies.forEachIndexed { index, currency ->
            MainScreen {
                currenciesList {
                    childAt<MainScreen.CurrencyItem>(index) {
                        name {
                            isDisplayed()
                            hasText(currency.name)
                        }
                        charCode {
                            isDisplayed()
                            hasText(currency.charCode)
                        }
                    }
                }
            }
        }
    }

    private fun openConverter(vararg currencies: Currency) {
        currencies.forEachIndexed { index, _ ->
            MainScreen {
                currenciesList {
                    childAt<MainScreen.CurrencyItem>(index) {
                        click()
                    }
                }
            }
        }
    }
}