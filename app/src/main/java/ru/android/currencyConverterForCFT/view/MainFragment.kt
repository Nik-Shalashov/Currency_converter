package ru.android.currencyConverterForCFT.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import ru.android.currencyConverterForCFT.R
import ru.android.currencyConverterForCFT.adapter.MainFragmentAdapter
import ru.android.currencyConverterForCFT.databinding.FragmentMainBinding
import ru.android.currencyConverterForCFT.model.CurrencyDTO
import ru.android.currencyConverterForCFT.viewModel.AppState
import ru.android.currencyConverterForCFT.viewModel.MainViewModel

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(currency: CurrencyDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction().add(R.id.container, ConverterFragment.newInstance(Bundle().apply {
                    putParcelable(ConverterFragment.BUNDLE_EXTRA, currency)
                }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewCurrencies.adapter = adapter
        viewModel.currenciesLiveData.observe(viewLifecycleOwner, {renderData(it)})
        viewModel.getCurrenciesFromRemoteSource()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val listTest = listOf(
                    appState.currencyData.Valute.AUD,
                    appState.currencyData.Valute.AZN,
                    appState.currencyData.Valute.GBP,
                    appState.currencyData.Valute.AMD,
                    appState.currencyData.Valute.BYN,
                    appState.currencyData.Valute.BGN,
                    appState.currencyData.Valute.BRL,
                    appState.currencyData.Valute.HUF,
                    appState.currencyData.Valute.HKD,
                    appState.currencyData.Valute.DKK,
                    appState.currencyData.Valute.USD,
                    appState.currencyData.Valute.EUR,
                    appState.currencyData.Valute.INR,
                    appState.currencyData.Valute.KZT,
                    appState.currencyData.Valute.CAD,
                    appState.currencyData.Valute.KGS,
                    appState.currencyData.Valute.CNY,
                    appState.currencyData.Valute.MDL,
                    appState.currencyData.Valute.NOK,
                    appState.currencyData.Valute.PLN,
                    appState.currencyData.Valute.RON,
                    appState.currencyData.Valute.XDR,
                    appState.currencyData.Valute.SGD,
                    appState.currencyData.Valute.TJS,
                    appState.currencyData.Valute.TRY,
                    appState.currencyData.Valute.TMT,
                    appState.currencyData.Valute.UZS,
                    appState.currencyData.Valute.UAH,
                    appState.currencyData.Valute.CZK,
                    appState.currencyData.Valute.SEK,
                    appState.currencyData.Valute.CHF,
                    appState.currencyData.Valute.ZAR,
                    appState.currencyData.Valute.KRW,
                    appState.currencyData.Valute.JPY)
                adapter.setCurrency(listTest)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                currenciesList.showSnackBar("Error!", "Reload", {viewModel.getCurrenciesFromRemoteSource()})
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(currency: CurrencyDTO)
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    companion object {
        fun newInstance() =
            MainFragment()
    }
}