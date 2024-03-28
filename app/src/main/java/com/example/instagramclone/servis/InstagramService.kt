package com.example.instagramclone.servis

import com.example.instagramclone.model.LoginModel
import com.example.instagramclone.model.RegisterModel
import com.example.instagramclone.model.VerificationCodeModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface InstagramService {
    @POST("v1/accounts:signUp?key=AIzaSyCWU9zF-tfKOc4gTAxzdb3y2Td0URCAqfw")
    fun register(@Body body: Map<String, Any?>): Call<RegisterModel>

    @POST("v1/accounts:signInWithPassword?key=AIzaSyCWU9zF-tfKOc4gTAxzdb3y2Td0URCAqfw")
    fun login(@Body body: Map<String, Any?>): Call<LoginModel>

    @POST("v1/accounts:sendOobCode?key=AIzaSyCWU9zF-tfKOc4gTAxzdb3y2Td0URCAqfw")
    fun sendCode(@Body body: Map<String, Any?>): Call<VerificationCodeModel>
}