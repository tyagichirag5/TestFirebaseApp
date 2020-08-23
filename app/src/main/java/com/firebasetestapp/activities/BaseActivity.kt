package com.firebasetestapp.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.firebasetestapp.R
import com.firebasetestapp.kotlin_extentions.showToast
import java.util.*

open class BaseActivity : AppCompatActivity() {//Common Class for Activities
    private var exit = false
    var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog()//Set up progress dialog
    }


    private fun exitFromApp() {
        finish()
        finishAffinity()
    }

    fun progressDialog() {
        progressDialog = Dialog(this)
        val view =
            View.inflate(this, R.layout.progress_dialog, null)
        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.setContentView(view)
        progressDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog!!.setCancelable(false)
    }

    open fun startProgressDialog() { //Method to start progress bar
        if (progressDialog != null && !progressDialog!!.isShowing) {
            try {
                progressDialog!!.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open fun stopProgressDialog() {//Method to stop progress bar
        if (progressDialog != null && progressDialog!!.isShowing) {
            try {
                progressDialog!!.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun exitApp() {
        if (exit) {
            exitFromApp() // finish activity
        } else {
            showToast(getString(R.string.press_to_exit))
            exit = true
            Handler(Looper.getMainLooper()).postDelayed(Runnable { exit = false }, 3 * 1000)
        }
    }



}