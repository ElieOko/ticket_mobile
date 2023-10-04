package com.odc.ticket.ui.components.home.transfers

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseFragment
import kotlinx.coroutines.*
import java.util.*

class SpecialFragment : BaseFragment(R.layout.fragment_special) {

    companion object{
        private const val Tag = "SpecialFragment"
    }

    private val scope = MainScope()

    private var signature: String? = null
    private var cardExpiryDate: String? = null
    private var cardPath: String? = null
    private var uniqueString: String? = null
    private var dialog: android.app.AlertDialog? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uniqueString = UUID.randomUUID().toString()
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_special, menu)
    }


    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}