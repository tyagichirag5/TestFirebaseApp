
package com.firebasetestapp.kotlin_extentions

import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText.isBlank(): Boolean {
    return this.text!!.isEmpty()
}

fun AppCompatEditText.checkString(): String {
    return this.text?.trim().toString()
}

fun AppCompatEditText.checkLength():Int{
    return this.text.toString().length
}

