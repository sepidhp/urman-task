package com.utechia.presentation.exchanges

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.utechia.domain.model.Exchange
import com.utechia.presentation.R
import com.utechia.presentation.databinding.ItemExchangeBinding

class ExchangeAdapter :
    RecyclerView.Adapter<ExchangeAdapter.FaqViewHolder>() {
    private var list = ArrayList<Exchange>()

    inner class FaqViewHolder(itemView: ItemExchangeBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val itemBinding: ItemExchangeBinding = itemView
        fun bind(data: Exchange) {
            itemBinding.apply {
                exchange = data
            }
        }
    }

    fun upDateDataSet(data: List<Exchange>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val binding = DataBindingUtil.inflate<ItemExchangeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_exchange, parent, false
        )
        return FaqViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0 else list.size
    }


    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(list[position])
    }
}