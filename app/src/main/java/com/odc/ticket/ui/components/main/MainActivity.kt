package com.odc.ticket.ui.components.main

import android.content.Intent
import android.os.Bundle
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseActivity
import com.odc.ticket.ui.components.login.LoginActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        startActivity(Intent(this, LoginActivity::class.java))
//        finish()
    }
}