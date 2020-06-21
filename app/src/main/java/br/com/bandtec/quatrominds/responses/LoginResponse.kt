package br.com.bandtec.quatrominds.responses

import br.com.bandtec.quatrominds.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status_code")
    var statusCode: Int,

    @SerializedName("Authorization")
    var authToken: String,

    @SerializedName("user")
    val user: User
)