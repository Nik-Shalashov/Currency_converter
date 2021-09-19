package ru.android.currencyConverterForCFT.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.android.currencyConverterForCFT.databinding.FragmentConverterBinding
import ru.android.currencyConverterForCFT.model.CurrencyDTO

class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currency = arguments?.getParcelable<CurrencyDTO>(BUNDLE_EXTRA)
        if (currency != null) {
            setData(currency)
        }
        binding.etRubles.setOnClickListener {
            val sum = if (binding.etRubles.text contentEquals "") {
                0.0
            } else {
                binding.etRubles.text.toString().toDouble()
            }
            val value: Double = currency?.Value ?: 0.0
            val calc = sum / value
            binding.etCurrency.setText(calc.toString())
        }
        binding.etCurrency.setOnClickListener {
            val sum = if (binding.etCurrency.text contentEquals "") {
                0.0
            } else {
                binding.etCurrency.text.toString().toDouble()
            }
            val value: Double = currency?.Value ?: 0.0
            val calc = sum * value
            binding.etRubles.setText(calc.toString())
        }
    }

    private fun setData(currencyData: CurrencyDTO) {
        binding.tvCurrencyName.text = currencyData.Name
        binding.tvCharCodeCurrency.text = currencyData.CharCode
        binding.tvCurrencyRate.text = currencyData.Value.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "currency"
        fun newInstance(bundle: Bundle): ConverterFragment {
            val fragment = ConverterFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}