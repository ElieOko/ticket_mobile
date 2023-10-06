package com.odc.ticket.ui.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.odc.ticket.R

abstract class BaseActivity1 : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MaterialTheme_NoActionBar)
        super.onCreate(savedInstanceState)
    }
}