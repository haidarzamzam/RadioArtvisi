package com.haidev.radioartvisi.main.home.viewmodels

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.navigation.Navigation
import com.haidev.radioartvisi.R
import com.haidev.radioartvisi.main.home.models.ListMainData

class ItemTvViewModel(
    val model: ListMainData
) {
    var nama: ObservableField<String?> = ObservableField(model.nama)
    var alamat: ObservableField<String?> = ObservableField(model.alamat)
    var logo: ObservableField<String?> = ObservableField(model.logo)

    fun onClick(view: View) {
        val controller = Navigation.findNavController(view)
        val bundle = bundleOf("data" to model)
        controller.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}

