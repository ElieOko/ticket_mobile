package com.odc.ticket.ui.components.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseActivity

class LoginActivity : BaseActivity() {
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_login)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
//        if (navHostFragment != null) navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp()
    }
}