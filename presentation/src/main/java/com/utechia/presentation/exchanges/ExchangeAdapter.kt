package com.utechia.presentation.exchanges

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.utechia.domain.model.Exchange
import com.utechia.presentation.R
import com.utechia.presentation.databinding.ItemExchangeBinding
import com.utechia.presentation.util.Utils

class ExchangeAdapter(private val activity: Activity) :
    RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {
    private var list = ArrayList<Exchange>()
    private var screenWidth = 0

    inner class ExchangeViewHolder(itemView: ItemExchangeBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val itemBinding: ItemExchangeBinding = itemView
        fun bind(data: Exchange) {
            itemBinding.apply {
                exchange = data
                position = layoutPosition
                size = list.size

                setThreeItemInEachScroll(itemBinding, itemView)

                setSelectedToText(itemBinding)
            }
        }
    }

    fun setSelectedToText(binding: ItemExchangeBinding) {
        binding.txtName.isSelected = true
        binding.txtBuyPrice.isSelected = true
        binding.txtSellPrice.isSelected = true
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
        screenWidth = Utils.calculateScreenWidth(activity)

        return ExchangeViewHolder(binding)
    }

    fun setThreeItemInEachScroll(binding: ItemExchangeBinding, view: View) {
        val itemWidth = screenWidth / 3

        val lp = binding.cnsRoot.layoutParams
        lp.height = lp.height
        lp.width = itemWidth
        view.layoutParams = lp
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0 else list.size
    }

    override fun onBindViewHolder(holder: ExchangeViewHolder, position: Int) {
        holder.bind(list[position])
    }
}