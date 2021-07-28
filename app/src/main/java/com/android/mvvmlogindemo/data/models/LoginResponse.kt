package com.android.mvvmlogindemo.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("fullname") val fullname: String,
    @SerializedName("image_profile") val image_profile: String,
    @SerializedName("token_type") val token_type: String,
    @SerializedName("expires_in") val expires_in: Int,
    @SerializedName("access_token") val access_token: String,
    @SerializedName("refresh_token") val refresh_token: String,
    @SerializedName("role") val role: String
)