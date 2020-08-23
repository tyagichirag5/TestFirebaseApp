package com.firebasetestapp.kotlin_extentions

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int
) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    activity?.supportFragmentManager?.inTransaction {
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    args: Bundle
) {
    supportFragmentManager.inTransaction {
        fragment.arguments = args
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

fun Fragment.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    args: Bundle
) {
    activity?.supportFragmentManager?.inTransaction {
        fragment.arguments = args
        replace(frameId, fragment)
        addToBackStack(fragment.javaClass.name)
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun isValidEmail(string: String): Boolean {
    return string.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())
}


fun Fragment.showToast(s: String) {
    Toast.makeText(activity?.applicationContext, s, Toast.LENGTH_SHORT).show()
}
fun AppCompatActivity.showToast(s: String) {
    Toast.makeText(this.applicationContext, s, Toast.LENGTH_SHORT).show()
}


fun Fragment.popBack() {
    activity?.supportFragmentManager?.popBackStack()
}