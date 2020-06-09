package com.haidev.radioartvisi.networks

import com.haidev.radioartvisi.main.home.models.ItemMainDataModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {
    @GET("exec?action=read")
    fun getListMainDataAsync(
    ): Observable<ItemMainDataModel>
}