package br.com.bandtec.quatrominds.services

import br.com.bandtec.quatrominds.model.Psicologo
import br.com.bandtec.quatrominds.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @Headers( "Content-Type: application/json")
    @POST("psicologos")
    fun createUser(
        @Body body: Psicologo
    ):Call<ResponseBody>


    @Headers( "Content-Type: application/json")
    @POST("login")
    fun userLogin(
        @Body body: User
    ):Call<ResponseBody>
}