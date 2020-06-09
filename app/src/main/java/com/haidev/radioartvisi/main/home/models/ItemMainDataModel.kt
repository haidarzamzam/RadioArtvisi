package com.haidev.radioartvisi.main.home.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ItemMainDataModel(
    val records: List<ListMainData>
)

@Parcelize
data class ListMainData(
    val alamat: String,
    val email: String,
    val gambar: String,
    val id_channel: Int,
    val kategori: String,
    val label_fb: String,
    val label_fp: String,
    val label_ig: String,
    val label_tw: String,
    val label_web: String,
    val label_youtube: String,
    val logo: String,
    val nama: String,
    val sms: String,
    val telp: String,
    val url: String,
    val url_fb: String,
    val url_fp: String,
    val url_ig: String,
    val url_peta: String,
    val url_tw: String,
    val url_web: String,
    val url_youtube: String,
    val whatsapp: String
) : Parcelable