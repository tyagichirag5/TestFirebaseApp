package com.firebasetestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebasetestapp.R
import com.firebasetestapp.activities.MainActivity
import com.firebasetestapp.databinding.FragmentAddDetailsBinding
import com.firebasetestapp.kotlin_extentions.*
import com.firebasetestapp.model_classes.UserData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddDetailsFragment : BaseFragment() {
    lateinit var binding: FragmentAddDetailsBinding
    private lateinit var database: DatabaseReference
    val AGE_LIMIT = 100
    var userID = ""
    var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {//Retrieve Data from previous fragment
            userID = it.getString("userID", "")
            email = it.getString("email", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (baseActivity as MainActivity).setToolbar(getString(R.string.add_details), true)
        binding = FragmentAddDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.clickHandle = this
        database = Firebase.database.reference //Initialize Firebase DB

    }

    override fun clickHandle(v: View) {
        when (v.id) {
            R.id.submitBT -> {
                if (isValidate()) { //Check Validations
                    writeToDatabase()
                }
            }
            R.id.detailsBT -> {
                replaceFragment(ViewDetailsFragment(), R.id.frameContainer)
            }
        }
    }

    private fun writeToDatabase() {
        val user = UserData(email, binding.nameET.checkString(), binding.ageET.checkString())
        database.child("users").child(userID).setValue(user)
        showToast(getString(R.string.data_added))
        popBack()
    }

    private fun isValidate(): Boolean {
        when {
            binding.nameET.isBlank() -> showToast(getString(R.string.enter_name))
            binding.ageET.isBlank() -> showToast(getString(R.string.enter_age))
            binding.ageET.checkString()
                .toInt() > AGE_LIMIT -> showToast(getString(R.string.enter_age_validation))
            else -> return true
        }
        return false
    }
}