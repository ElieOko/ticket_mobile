package com.odc.ticket.ui.components.home.setting

//import android.os.Bundle
//import android.view.View
//import androidx.preference.Preference
//import androidx.preference.PreferenceFragmentCompat
//import androidx.preference.PreferenceManager
//import com.odc.ticket.R
//
//
//class SettingPreferencesFragment : PreferenceFragmentCompat() {
//
//    private var onItemClickListener: OnItemClickListener? = null
//
//    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        setPreferencesFromResource(R.xml.setting_preferences, rootKey)
//    }
//
//
//    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
//        this.onItemClickListener = onItemClickListener
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val localData = LocalData()
//        val defaultPrinter = findPreference<Preference>("default_printer")
//        if (defaultPrinter != null && context != null) {
//            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
//            val defaultText = sharedPrefs.getString("default_printer", null)
//            defaultPrinter.summary = defaultText
//            defaultPrinter.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _: Preference?, newValue: Any ->
//                defaultPrinter.summary = newValue.toString()
//                true
//            }
//        }
//        val bluetoothPrinter = findPreference<Preference>("bluetooth_printer")
//        if (bluetoothPrinter != null) {
//            val bluetooth = localData.getString("bluetooth_printer")
//            bluetoothPrinter.summary = bluetooth
//            bluetoothPrinter.onPreferenceClickListener = Preference.OnPreferenceClickListener {
//                onItemClickListener?.onBluetoothClicked()
//                true
//            }
//        }
//        val wifiPrinter = findPreference<Preference>("wifi_printer")
//        if (wifiPrinter != null) {
//            val wifi = localData.getString("wifi_printer")
//            wifiPrinter.summary = wifi
//            wifiPrinter.onPreferenceClickListener = Preference.OnPreferenceClickListener {
//                onItemClickListener?.onWifiClicked()
//                true
//            }
//        }
//        val usbPrinter = findPreference<Preference>("usb_printer")
//        if (usbPrinter != null) {
//            val usb = localData.getString("usb_printer")
//            usbPrinter.summary = usb
//            usbPrinter.onPreferenceClickListener = Preference.OnPreferenceClickListener {
//                onItemClickListener?.onUsbClicked()
//                true
//            }
//        }
//    }
//
//    interface OnItemClickListener {
//        fun onBluetoothClicked() {}
//        fun onWifiClicked() {}
//        fun onUsbClicked() {}
//    }
//}