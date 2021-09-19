package ru.android.currencyConverterForCFT.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.android.currencyConverterForCFT.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}