package com.firebasetestapp.model_classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserData(
    var email: String? = "",
    var name: String? = "",
    var age: String? = ""
) {
}