package com.odc.ticket.ui.components

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment{

        constructor() : super()
        constructor(layoutId: Int) : super(layoutId)

}