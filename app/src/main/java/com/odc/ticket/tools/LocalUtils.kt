package com.odc.ticket.tools

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.odc.ticket.R
import com.odc.ticket.app.SoficomTicket
import com.odc.ticket.data.prefs.LocalData
import java.io.File
import java.util.*

object LocalUtils {

    fun checkPermission(context: Context, permissions: Array<String>): Boolean {
        var result = true
        for (permission in permissions) {
            val grantResult = context.checkCallingOrSelfPermission(permission)
            if (grantResult != PackageManager.PERMISSION_GRANTED) result = false
        }
        return result
    }

    fun getJsonHeaders():MutableMap<String,String>{
        if(SoficomTicket.getInstance().user?.tokenString != null) {
            return mapOf("Content-Type" to "application/json;+charset=UTF-8", "Accept" to "application/json;+charset=UTF-8",
                "Authorization" to "Bearer ${SoficomTicket.getInstance().user?.tokenString}", "ClientType" to "Mobile",
                "User-Agent" to "Mozilla/5.0").toMutableMap()
        }
        return mapOf("Content-Type" to "application/json;+charset=UTF-8", "Accept" to "application/json;+charset=UTF-8",
            "User-Agent" to "Mozilla/5.0").toMutableMap()
    }

    fun getTempFile(context: Context?) : File? {
        if(context==null) return null
        return File(context.cacheDir, "${UUID.randomUUID()}.tmp")
    }

    fun createLoadingDialog(context: Context) : AlertDialog {
        val dialogView = View.inflate(context, R.layout.dialog_loading, null)
        return MaterialAlertDialogBuilder(context).apply {
            setView(dialogView)
            setCancelable(false)
        }.create()
    }

    @SuppressLint("HardwareIds")
    fun getSerialNumber(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).uppercase()
    }

    fun getTicketBaseUrl(): String{
        return "https://${LocalData().getString(LocalConstants.TICKET_URL)}"

    }

    fun getClientBaseUrl(): String{

        return "https://${LocalData().getString(LocalConstants.CLIENT_URL)}"
    }

    fun errorDialog(context: Context?, title: String, text: String) {
        val thisContext = context ?: return
        MaterialAlertDialogBuilder(thisContext)
            .setTitle(title)
            .setMessage(text)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun noConnectionError(context: Context?) {
        val thisContext = context ?: return
        MaterialAlertDialogBuilder(thisContext)
            .setTitle(R.string.no_connection_error_title)
            .setMessage(R.string.no_connection_error_text)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun timeoutError(context: Context?) {
        val thisContext = context ?: return
        MaterialAlertDialogBuilder(thisContext)
            .setTitle(R.string.time_out_error_title)
            .setMessage(R.string.time_out_error_text)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun serverError(context: Context?) {
        val thisContext = context ?: return
        MaterialAlertDialogBuilder(thisContext)
            .setTitle(R.string.server_error_title)
            .setMessage(R.string.server_error_text)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun networkError(context: Context?) {
        val thisContext = context ?: return
        MaterialAlertDialogBuilder(thisContext)
            .setTitle(R.string.network_error_title)
            .setMessage(R.string.network_error_text)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun authFailureError(context: Context?,okListener: (() -> Unit)?) {
        val thisContext = context ?: return
        MaterialAlertDialogBuilder(thisContext)
            .setTitle(R.string.authentication_error_title)
            .setMessage(R.string.authentication_error_text)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                okListener?.invoke()
                dialog.dismiss()
            }.show()
    }
}