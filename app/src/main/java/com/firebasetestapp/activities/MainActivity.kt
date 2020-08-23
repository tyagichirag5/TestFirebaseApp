package com.firebasetestapp.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager
import com.firebasetestapp.R
import com.firebasetestapp.fragments.LoginFragment
import com.firebasetestapp.kotlin_extentions.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar("", true) //Set Up Toolbar
        openLoginFragment()          //Calling Login Fragment First
    }

    fun setToolbar(title: String, isVisible: Boolean) {
        setSupportActionBar(toolbarTB)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        titleTV.text = title

        when {
            isVisible -> {
                toolbarTB.visibility = View.VISIBLE
            }
            else -> toolbarTB.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() { //Custom Back press for fragments load in activity
        val fragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
        when {
            fragment is LoginFragment -> {
                exitApp()
            }
            supportFragmentManager.backStackEntryCount > 0 -> {
                supportFragmentManager.popBackStack()
            }
            else -> {
                supportFragmentManager.popBackStack(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                openLoginFragment()
            }
        }

    }

    private fun openLoginFragment() {
        replaceFragment(LoginFragment(), R.id.frameContainer)
    }

}