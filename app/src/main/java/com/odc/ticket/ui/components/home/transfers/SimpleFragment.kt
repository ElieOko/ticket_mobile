package com.odc.ticket.ui.components.home.transfers

import android.os.Bundle
import android.view.View
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseFragment
import kotlinx.coroutines.MainScope
import java.util.*

class SimpleFragment : BaseFragment(R.layout.fragment_simple) {

    companion object{
        private const val Tag = "SimpleFragment"
    }

    private val scope = MainScope()

    private var page: String? = null
    private var transferTypeId: Long = 0
    private var currencyId: Long = 0
    private var uniqueString: String? = null
    private var dialog: android.app.AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uniqueString = UUID.randomUUID().toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}