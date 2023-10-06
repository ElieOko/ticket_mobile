package com.odc.ticket.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.odc.ticket.app.SoficomTicket
import com.odc.ticket.tools.AppConstants


class LocalData {
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    constructor() {
        preferences = SoficomTicket.getInstance().getSharedPreferences(AppConstants.SP_NAME, Context.MODE_PRIVATE)
        editor = preferences.edit()
        editor.apply()
        editor.commit()
    }

    constructor(context: Context) {
        preferences = context.getSharedPreferences(AppConstants.SP_NAME, Context.MODE_PRIVATE)
        editor = preferences.edit()
        editor.apply()
        editor.commit()
    }

    fun setString(key: String?, value: String?) {
        if (value == null) editor.remove(key) else editor.putString(key, value)
        editor.apply()
        editor.commit()
    }

    fun getString(key: String?): String? {
        return preferences.getString(key, null)
    }
}