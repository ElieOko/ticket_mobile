package com.odc.ticket.ui.components.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.odc.ticket.R
import com.odc.ticket.tools.LocalExtensions.findParentFragment
import com.odc.ticket.ui.components.BaseFragment
import com.odc.ticket.ui.components.login.LoginActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var modeModel: ModeModel? = null
    private val scope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(modeModel==null){
            modeModel = ModeModel.Simple
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setTitle(R.string.home)
        toolbar.setOnMenuItemClickListener { return@setOnMenuItemClickListener onOptionsItemSelected(it) }
        initMode()
    }

    private fun launchLogin() {
        val thisContext = context ?: return
        val dialogView = View.inflate(thisContext,R.layout.dialog_login, null)
        val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
        val passwordLayout: TextInputLayout = dialogView.findViewById(R.id.passwordLayout)
        val passEditText: TextInputEditText = dialogView.findViewById(R.id.passEditText)
        val dialog = MaterialAlertDialogBuilder(thisContext).apply {
            setView(dialogView)
            setPositiveButton(R.string.login,null)
            setNegativeButton(R.string.cancel,null)
        }.create()
        dialog.setOnShowListener{
            val saveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
                isAllCaps = false
            }
            val cancelButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
                isAllCaps = false
            }
//            saveButton.setOnClickListener {
//                val name = SoficomTicket.getInstance().user.userName
//                val pass = passEditText.text?.toString()
//
//                if((pass==null || pass.trim().isEmpty())){
//                    if((pass==null || pass.trim().isEmpty())) passwordLayout.error = "Error"
//                    return@setOnClickListener
//                }
//                saveButton.isEnabled = false
//                cancelButton.isEnabled = false
//                progressBar.visibility = View.VISIBLE
//                passEditText.isEnabled = false
//                val userModel = UserModel()
//                userModel.userName = name
//                userModel.password = pass
//
//                scope.launch {
//                    try{
//                        UserService.login(requireContext(), UserViewModel(name, pass))
//                        saveButton.isEnabled = true
//                        cancelButton.isEnabled = true
//                        progressBar.visibility = View.GONE
//                        passEditText.isEnabled = true
//                        modeModel = ModeModel.Simple
//                        initMode()
//                        dialog.dismiss()
//                    } catch (ex: Exception){
//                        saveButton.isEnabled = true
//                        cancelButton.isEnabled = true
//                        progressBar.visibility = View.GONE
//                        passEditText.isEnabled = true
//                    }
//                }
//            }
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
    private fun initMode() {
        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.menu?.clear()
        toolbar?.inflateMenu(R.menu.fragment_home)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.homeContainer) as NavHostFragment
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.fragment_home_xml, true).build()
        when(modeModel){
            ModeModel.Simple -> {
                navHostFragment.navController.navigate(R.id.simpleFragment, null, navOptions)
            }
            ModeModel.Advanced -> {
                navHostFragment.navController.navigate(R.id.advancedFragment, null, navOptions)
            }
            ModeModel.SimpleClient -> {
                navHostFragment.navController.navigate(R.id.clientFragment, null, navOptions)
            }
            else -> {
                navHostFragment.navController.navigate(R.id.welcomeFragment, null, navOptions)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_simple ->{
                modeModel = ModeModel.Simple
                Toast.makeText(requireContext(),"ElieOko",Toast.LENGTH_LONG).show()
                initMode()
                 true
            }
            R.id.action_advanced -> {
                modeModel = ModeModel.Advanced
                initMode()
                return true
            }
            R.id.action_simple_client -> {
                if(modeModel==ModeModel.SimpleClient){
                    //launchLogin()
                } else {
                    modeModel = ModeModel.SimpleClient
                    initMode()
                }
                return true
            }
            R.id.action_add_client -> {
                NavHostFragment.findNavController(this@HomeFragment).navigate(R.id.action_home_to_person)
                return true
            }
            R.id.action_sync -> {
               // load()
                return true
            }
            R.id.action_settings -> {
                NavHostFragment.findNavController(this@HomeFragment).navigate(R.id.action_home_to_setting)
                return true
            }
            R.id.action_server_settings -> {
              //  showServerSetting(null)
                return true
            }
            R.id.action_server_clients_settings -> {
               // showClientServerSetting()
                return true
            }
            R.id.action_version_settings -> {
              //  showVersionSetting()
                return true
            }
            R.id.action_log_out -> {
                Toast.makeText(requireContext(),"test cool",Toast.LENGTH_LONG).show()
                val thisActivity = activity as AppCompatActivity?
                if (thisActivity != null) {
                    val intent = Intent(thisActivity, LoginActivity::class.java)
                    startActivity(intent)
                    thisActivity.finish()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    enum class ModeModel {
        Simple, Advanced, SimpleClient
    }
    class WelcomeFragment : BaseFragment(R.layout.fragment_home_welcome)

    class SimpleFragment : BaseFragment(R.layout.fragment_home_simple){
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            with(view.findViewById<View>(R.id.riaSmallSimpleButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Ria")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 3).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }

                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.riaBigSimpleButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Ria")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 5).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.moneyTransSimpleButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "MoneyTrans")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 6).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
//                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.moneyGramSimpleButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "MoneyGram")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 7).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.sendUSDSimpleButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Envoi")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 1).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.sendCDFSimpleButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Envoi")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 1).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 2).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.receiveUSDSimpleButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Retrait")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 2).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.receiveCDFSimpleButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Retrait")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 2).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 2).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.specialUSDSimpleButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Special")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 4).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.specialCDFSimpleButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putString("page", "Special")
//                        val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 4).build().findUnique()
//                        if (transferTypeModel != null) {
//                            bundle.putParcelable("transferTypeModel", transferTypeModel)
//                        }
//                        val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 2).build().findUnique()
//                        if (currencyModel != null) {
//                            bundle.putParcelable("currencyModel", currencyModel)
//                        }
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_simple, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.cancelSimpleButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_cancel)
                    }
                }
            }
        }
    }

    class AdvancedFragment : BaseFragment(R.layout.fragment_home_advanced){
        private val scope = MainScope()
        override fun onViewCreated(view: View, savedInstanceState: Bundle?){
            with(view.findViewById<View>(R.id.sendButton)){
//                if(SoficomTicket.getInstance().user?.userType == "International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_send)
                    }
                }
            }

            with(view.findViewById<View>(R.id.receiveButton)){
//                if(SoficomTicket.getInstance().user?.userType == "International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_receive)
                    }
                }
            }

            with(view.findViewById<View>(R.id.riaSmallButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putInt("type", 3)
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_ria, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.riaBigButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putInt("type", 5)
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_ria, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.moneyTransButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putInt("type", 6)
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_ria, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.moneyGramButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        val bundle = Bundle()
                        bundle.putInt("type", 7)
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_ria, bundle)
                    }
                }
            }

            with(view.findViewById<View>(R.id.specialButton)){
//                if(SoficomTicket.getInstance().user?.userType == "International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_special)
                    }
                }
            }

            with(view.findViewById<View>(R.id.evalButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_evaluation)
                    }
                }
            }

            with(view.findViewById<View>(R.id.cancelButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_cancel)
                    }
                }
            }

        }
    }

    class ClientFragment : BaseFragment(R.layout.fragment_home_client){
        private val scope = MainScope()
        override fun onViewCreated(view: View, savedInstanceState: Bundle?){
            with(view.findViewById<View>(R.id.riaSimpleClientButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 3).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                   // putParcelable("transferModel", model)
                                    putString("page", "Ria")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                               // setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.moneyTransSimpleClientButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 6).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                   // putParcelable("transferModel", model)
                                    putString("page", "Ria")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                                //setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.moneyGramSimpleClientButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 7).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                   // putParcelable("transferModel", model)
                                    putString("page", "Money Gram")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                               // setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.sendCDFSimpleClientButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 1).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 2).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                    //putParcelable("transferModel", model)
                                    putString("page", "Envoi")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                               // setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.sendUSDSimpleClientButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 1).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                    //putParcelable("transferModel", model)
                                    putString("page", "Envoi")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                                //setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.receiveCDFSimpleClientButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 2).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 2).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                   // putParcelable("transferModel", model)
                                    putString("page", "Retrait")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                                //setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.receiveUSDSimpleClientButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 2).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                    //putParcelable("transferModel", model)
                                    putString("page", "Retrait")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                                //setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.specialCDFSimpleClientButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 4).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 2).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                   // putParcelable("transferModel", model)
                                    putString("page", "Special")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                                //setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.specialUSDSimpleClientButton)){
//                if(SoficomTicket.getInstance().user?.userType=="International") {
//                    visibility = View.GONE
//                }
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        scope.launch {
                            try {
//                                setLoading(true)
//                                val viewModel = TransferViewModel()
//                                viewModel.branch = SoficomTicket.getInstance().user.branch
//                                viewModel.amount = 0.0
//                                val transferTypeModel = transferTypeBox.query().equal(TransferTypeModel_.id, 4).build().findUnique()
//                                if (transferTypeModel != null) {
//                                    viewModel.transferTypeId = transferTypeModel.id
//                                }
//                                val currencyModel = currencyBox.query().equal(CurrencyModel_.id, 1).build().findUnique()
//                                if (currencyModel != null) {
//                                    viewModel.currencyId = currencyModel.id
//                                }
//                                val model = TransferService.save(requireContext(), viewModel)
//                                setLoading(false)
                                val bundle = Bundle().apply {
                                   // putParcelable("transferModel", model)
                                    putString("page", "Retrait")
                                }
                                val options = NavOptions.Builder().setPopUpTo(R.id.homeFragment, false).build()
                                NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_printer, bundle, options)
                            } catch (ex: Exception){
                                //setLoading(false)
                            }
                        }
                    }
                }
            }

            with(view.findViewById<View>(R.id.cancelSimpleClientButton)){
                setOnClickListener {
                    findParentFragment<HomeFragment>()?.let { fragment ->
                        NavHostFragment.findNavController(fragment).navigate(R.id.action_home_to_cancel)
                    }
                }
            }
        }
    }
}