package com.example.instagramclone.view_model

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.instagramclone.model.LoginModel
import com.example.instagramclone.servis.InstagramService
import com.example.instagramclone.utils.Utils
import com.example.instagramclone.view.HomePageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel : ViewModel() {
    private val loginMap: HashMap<String, Any> = HashMap<String, Any>()
    fun login(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
        val retrofit = Retrofit.Builder().baseUrl(Utils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(InstagramService::class.java)
        /*     val body = mapOf(
                 "email" to binding.loginUsernameTextField.text.toString(),
                 "password" to binding.loginPasswordTextField.text.toString(),
                 "returnSecureToken" to true
             )*/
        loginMap["email"] = email
        loginMap["password"] = password
        service.login(loginMap).enqueue(
            object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    if (response.isSuccessful) {
                        /* response.body()?.let {
                             val intent = Intent(context, HomePageActivity::class.java)
                             startActivity(intent)
                             activity?.finish();
                         }*/
                        onSuccess();
                    } else if (response.code() == 400) {
                        onError()
                    }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    Log.e("Error", "login Error ", t)
                }
            },
        )
    }
}