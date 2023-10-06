package com.odc.ticket.ui.components.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
//import com.google.firebase.FirebaseApp
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseActivity

class HomeActivity : BaseActivity() {
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        FirebaseApp.initializeApp(this)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
        if (navHostFragment != null) navController = navHostFragment.navController
        instance = this
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp()
    }

    companion object {
        var instance: HomeActivity? = null
            private set
    }
}