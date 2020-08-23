package com.firebasetestapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebasetestapp.R
import com.firebasetestapp.activities.MainActivity
import com.firebasetestapp.databinding.FragmentHomeBinding
import com.firebasetestapp.kotlin_extentions.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (baseActivity as MainActivity).setToolbar(getString(R.string.login), true)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.clickHandle = this
        auth = Firebase.auth

    }

    override fun clickHandle(v: View) {
        when (v.id) {
            R.id.loginBT -> {
                if (isValidate()) {
                    baseActivity!!.startProgressDialog()
                    createAccount()
                }
            }
        }
    }

    private fun createAccount() {
        auth.createUserWithEmailAndPassword(
            binding.emailET.checkString(),
            binding.passwordET.checkString()
        ).addOnCompleteListener(baseActivity!!) { task ->
            if (task.isSuccessful) {
                baseActivity!!.stopProgressDialog()
                // Sign in success, goto Add Details Screen
                Log.e("FIREBASE", "createUserWithEmail:success")
                val user = auth.currentUser
                showToast(getString(R.string.login_success))
                val bundle = Bundle()
                bundle.putString("userID", user?.uid)
                bundle.putString("email", binding.emailET.checkString())
                replaceFragment(AddDetailsFragment(), R.id.frameContainer, bundle)
            } else {
                baseActivity!!.stopProgressDialog()
                // If sign in fails, display a message to the user.
                Log.e("FIREBASE", "createUserWithEmail:failure", task.exception)
                showToast(getString(R.string.auth_failed) + " " + task.exception)
            }
        }
    }

    private fun isValidate(): Boolean {
        when {
            binding.emailET.isBlank() -> showToast("Please enter email")
            !isValidEmail(binding.emailET.checkString()) -> showToast("Please enter valid email")
            binding.passwordET.isBlank() -> showToast("Please enter password")
            binding.passwordET.checkLength() < 6 -> showToast("Please enter password of min 6 characters")
            else -> return true
        }
        return false
    }

}