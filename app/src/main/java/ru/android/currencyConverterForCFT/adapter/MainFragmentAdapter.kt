package ru.android.currencyConverterForCFT.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.android.currencyConverterForCFT.R
import ru.android.currencyConverterForCFT.model.CurrencyDTO
import ru.android.currencyConverterForCFT.view.MainFragment

class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?): RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var currencyData: List<CurrencyDTO> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setCurrency(data: List<CurrencyDTO>) {
        currencyData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentAdapter.MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MainFragmentAdapter.MainViewHolder, position: Int) {
        holder.bind(currencyData[position])
    }

    override fun getItemCount(): Int = currencyData.size

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(currency: CurrencyDTO) {
            itemView.apply {
                findViewById<TextView>(R.id.tv_item_currency_name).text = currency.Name
                findViewById<TextView>(R.id.tv_item_currency_char_code).text = currency.CharCode
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(currency)
                }
            }
        }
    }
}