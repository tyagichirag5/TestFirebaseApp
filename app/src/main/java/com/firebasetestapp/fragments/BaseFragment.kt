package com.firebasetestapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.firebasetestapp.activities.BaseActivity
import com.firebasetestapp.utils.HandleClick


open class BaseFragment : Fragment(), HandleClick { //Common Class for Fragments
    var baseActivity: BaseActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity?
    }

    override fun clickHandle(v: View) {

    }


}