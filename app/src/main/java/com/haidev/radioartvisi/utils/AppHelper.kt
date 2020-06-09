package com.haidev.radioartvisi.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.haidev.radioartvisi.R
import de.mateware.snacky.Snacky

object AppHelper {
    fun snackySuccess(view: View, message: String) {
        Snacky.builder()
            .setView(view)
            .setText(message)
            .setDuration(Snacky.LENGTH_SHORT)
            .success()
            .show()
    }

    fun snackyError(view: View, message: String) {
        Snacky.builder()
            .setView(view)
            .setText(message)
            .setDuration(Snacky.LENGTH_SHORT)
            .error()
            .show()
    }

    fun snackyWarning(view: View, message: String) {
        Snacky.builder()
            .setView(view)
            .setText(message)
            .setDuration(Snacky.LENGTH_SHORT)
            .warning()
            .show()
    }

    private lateinit var builder: AlertDialog.Builder
    private lateinit var loading: AlertDialog

    fun showLoading(context: Context) {
        val view = context.layoutInflater.inflate(R.layout.custom_loading, null)
        builder = AlertDialog.Builder(context)
        builder.setView(view)
        builder.setCancelable(false)
        loading = builder.create()
        loading.show()

        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels
        val dialogWindowWidth = (displayWidth * 0.35f).toInt()
        loading.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loading.window?.setLayout(dialogWindowWidth, dialogWindowWidth)
    }

    fun hideLoading() {
        loading.dismiss()
    }

    private inline val Context.layoutInflater: android.view.LayoutInflater
        get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater

    private val Context.windowManager: WindowManager
        get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager
}