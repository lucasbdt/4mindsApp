package br.com.bandtec.quatrominds.services

public interface Session {

    fun isLoggedIn(): Boolean

    fun saveToken(token: String?)

    fun getToken(): String?

    fun saveEmail(email: String?)

    fun getEmail(): String?

    fun saveSenha(password: String?)

    fun getSenha(): String?

    fun invalidate()
}