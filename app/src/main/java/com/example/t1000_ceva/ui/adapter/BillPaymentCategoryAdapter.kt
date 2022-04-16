package com.example.t1000_ceva.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.t1000_ceva.databinding.BillPaymentTypeListItemBinding
import com.example.t1000_ceva.domain.BillPaymentTypeItem

class BillPaymentCategoryAdapter(
    private val context: Context,
    private val onClick: ((billPaymentTypeItem: BillPaymentTypeItem) -> Unit)? = null
) : ListAdapter<BillPaymentTypeItem, BillPaymentCategoryAdapter.BillPaymentCategoryViewHolder>(
    historyDiffUtil
) {

    companion object {
        val historyDiffUtil = object : DiffUtil.ItemCallback<BillPaymentTypeItem>() {
            override fun areItemsTheSame(
                oldItem: BillPaymentTypeItem,
                newItem: BillPaymentTypeItem
            ) = oldItem.paymentTypeName == newItem.paymentTypeName && oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: BillPaymentTypeItem,
                newItem: BillPaymentTypeItem
            ) = areItemsTheSame(oldItem, newItem)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BillPaymentCategoryViewHolder {
        val binding =
            BillPaymentTypeListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BillPaymentCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BillPaymentCategoryViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.populateView(item)

            holder.itemView.setOnClickListener {
                onClick?.invoke(item)
            }

        }
    }

    inner class BillPaymentCategoryViewHolder(val binding: BillPaymentTypeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateView(item: BillPaymentTypeItem) {
            binding.titleView.text = item.paymentTypeName
        }
    }
}