package com.odc.ticket.tools

import androidx.fragment.app.Fragment

object LocalExtensions {
    inline fun <reified T: Fragment> Fragment.findParentFragment() : T?{
        val list = ArrayList<Fragment>()
        findParents(list)
        for (fragment in list){
            if(fragment is T){
                return fragment
            }
        }
        return null
    }

    fun Fragment.findParents(arrayList: ArrayList<Fragment>) {
        parentFragment?.let {
            arrayList.add(it)
            if(it.parentFragment!=null){
                it.findParents(arrayList)
            }
        }
    }

}