package com.example.jetpackapp.model

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("token_type")
    var tokenType: String? = null;
    @SerializedName("expires_in")
    var expiresIn: Long = 0;
    @SerializedName("access_token")
    var accessToken: String? = null

}