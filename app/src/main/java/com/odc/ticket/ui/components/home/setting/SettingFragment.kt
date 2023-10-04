package com.odc.ticket.ui.components.home.setting

import android.os.Bundle
import android.view.View
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseFragment

class SettingFragment : BaseFragment(R.layout.fragment_setting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}