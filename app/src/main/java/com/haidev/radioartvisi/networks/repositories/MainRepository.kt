package com.haidev.radioartvisi.networks.repositories

import com.haidev.radioartvisi.main.home.models.ItemMainDataModel
import com.haidev.radioartvisi.networks.ApiObserver
import com.haidev.radioartvisi.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepository {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    fun getListMainDataAsync(
        onResult: (ItemMainDataModel) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        apiService.getListMainDataAsync()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<ItemMainDataModel>(compositeDisposable) {
                override fun onApiSuccess(data: ItemMainDataModel) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun cleared() {
        compositeDisposable.clear()
    }
}