package com.haidev.radioartvisi.main.home.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.haidev.radioartvisi.main.home.models.ItemMainDataModel
import com.haidev.radioartvisi.networks.repositories.MainRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var isLoading: ObservableField<Boolean> = ObservableField()
    var mainDataResponse: MutableLiveData<ItemMainDataModel> = MutableLiveData()
    var mainDataError: MutableLiveData<Throwable> = MutableLiveData()

    private var mainRepository = MainRepository()

    fun getListMainDataAsync() {
        isLoading.set(true)
        mainRepository.getListMainDataAsync(
            {
                isLoading.set(false)
                mainDataResponse.postValue(it)
            }, {
                isLoading.set(false)
                mainDataError.postValue(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        mainRepository.cleared()
    }
}