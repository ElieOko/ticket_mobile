package com.odc.ticket.ui.components.login

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.odc.ticket.R
import com.odc.ticket.ui.components.BaseFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    companion object{
        private const val Tag = "LoginFragment"
    }

    private val scope = MainScope()

    private var dialog: android.app.AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setTitle(R.string.sign_in)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)

        view.findViewById<AppCompatTextView>(R.id.nameTextView).text = "1.1.1"
        val usernameLayout = view.findViewById<TextInputLayout>(R.id.usernameLayout)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.passwordLayout)
        val nameEditText = view.findViewById<TextInputEditText>(R.id.nameEditText)
        val passEditText = view.findViewById<TextInputEditText>(R.id.passEditText)
        val errorTextView = view.findViewById<MaterialTextView>(R.id.errorTextView)

        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                usernameLayout.error = null
            }
        })
        passEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                passwordLayout.error = null
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_login, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_server_settings) {
            //showServerSetting(null)
            return true
        } else if (item.itemId == R.id.action_version_settings) {
            //showVersionSetting()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showVersionSetting() {
        val activity = activity as AppCompatActivity?
        if (activity != null) {
            val dialogBuilder = AlertDialog.Builder(activity)
            dialogBuilder.setCancelable(false)
            dialogBuilder.setTitle("Version")
            dialogBuilder.setMessage("1.1.2")
            dialogBuilder.setPositiveButton(R.string.ok) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            dialogBuilder.create().show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.cancel()
    }
}