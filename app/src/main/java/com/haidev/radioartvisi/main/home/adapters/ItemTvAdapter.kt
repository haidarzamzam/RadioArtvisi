package com.haidev.radioartvisi.main.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haidev.radioartvisi.R
import com.haidev.radioartvisi.databinding.ItemTvBinding
import com.haidev.radioartvisi.main.home.models.ListMainData
import com.haidev.radioartvisi.main.home.viewmodels.ItemTvViewModel

class ItemTvAdapter(
    private val context: Context,
    private var listMainData: MutableList<ListMainData>
) :
    RecyclerView.Adapter<ItemTvAdapter.ItemRadioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemRadioViewHolder {
        val binding: ItemTvBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_tv,
                parent,
                false
            )
        return ItemRadioViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return listMainData.size
    }

    override fun onBindViewHolder(holder: ItemRadioViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bindBinding(listMainData[fixPosition])
    }

    class ItemRadioViewHolder(val binding: ItemTvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var viewModel: ItemTvViewModel

        fun bindBinding(model: ListMainData) {
            viewModel =
                ItemTvViewModel(
                    model
                )
            binding.data = viewModel
            binding.executePendingBindings()
        }

    }
}