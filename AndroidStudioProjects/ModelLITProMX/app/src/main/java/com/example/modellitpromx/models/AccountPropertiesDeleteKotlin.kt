package com.example.modellitpromx.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class AccountPropertiesDeleteKotlin (

    @SerializedName("username")
    @Expose
    var email: String,

    @SerializedName("firstName")
    @Expose
    var firstName: String,

    @SerializedName("lastName")
    @Expose
    var lastName: String

)