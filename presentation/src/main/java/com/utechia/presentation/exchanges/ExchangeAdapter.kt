package com.utechia.presentation.exchanges

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.utechia.domain.model.Exchange
import com.utechia.presentation.R
import com.utechia.presentation.databinding.ItemExchangeBinding

class ExchangeAdapter :
    RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {
    private var list = ArrayList<Exchange>()

    inner class ExchangeViewHolder(itemView: ItemExchangeBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val itemBinding: ItemExchangeBinding = itemView
        fun bind(data: Exchange) {
            itemBinding.apply {
                exchange = data
                position = layoutPosition
            }
        }
    }

    fun updateDataSet(data: List<Exchange>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder {
        val binding = DataBindingUtil.inflate<ItemExchangeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_exchange, parent, false
        )
        return ExchangeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0 else list.size
    }

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        holder.bind(list[position])
    }
}