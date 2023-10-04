package com.odc.ticket.ui.components.home.transfers

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseFragment
import kotlinx.coroutines.*

class ReceiveFragment : BaseFragment(R.layout.fragment_receive) {

    companion object{
        private const val Tag = "ReceiveFragment"
    }

    private var isBranchCreated = false

    private enum class TypeModel { Ria, Normal }

    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_receive, menu)
    }


    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}