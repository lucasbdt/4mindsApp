package br.com.bandtec.quatrominds.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.bandtec.quatrominds.R
import br.com.bandtec.quatrominds.model.User
import br.com.bandtec.quatrominds.services.ClientFourMinds
import br.com.bandtec.quatrominds.storage.SessionManager
import br.com.bandtec.quatrominds.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        sessionManager = SessionManager(this)

        btn_login.setOnClickListener {

            val email = username.text.toString().trim()
            val password = senha.text.toString().trim()

            val user = User(email, password)


              ClientFourMinds.instance.userLogin(user)
                    .enqueue(
                        object : Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                                val headers: Headers = response.headers()
                                // get header value
                                // get header value
                                val auth = response.headers()["Authorization"]
                                Log.d("Body", call.request().toString())
                                Log.d("Status Code",response.code().toString())
                                Log.d("raw input",response.raw().toString())
                                Log.d("Response",response.headers().toString())
                                if (auth != null) {
                                    Log.d("Response",auth.substring(7))
                                }


                                if (response.isSuccessful) {

                                    if (auth != null) {
                                        sessionManager.saveAuthToken(auth.substring(7))
                                    }

                                    Toast.makeText(applicationContext, "Login success ", Toast.LENGTH_LONG)

                                    val intent = Intent(applicationContext, MenuActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                    startActivity(intent)


                                } else {
                                    Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_LONG)
                                        .show()
                                }

                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                            }
                        })

        }
    }

    fun mudarCadastroProfissional(v: View){
        val intent = Intent(applicationContext, CadastroProfissionalActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun mudarEsqueciSenha(v: View){
        val intent = Intent(applicationContext, EsqueciSenhaActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }



    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

}






