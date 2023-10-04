package com.odc.ticket.ui.components.home.transfers

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseFragment

class PrinterFragment : BaseFragment(R.layout.fragment_printer) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_printer, menu)
    }


}