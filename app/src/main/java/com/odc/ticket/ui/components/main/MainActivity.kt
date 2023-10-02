package com.odc.ticket.ui.components.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}