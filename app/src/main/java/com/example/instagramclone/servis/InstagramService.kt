package com.example.instagramclone.servis

import com.example.instagramclone.model.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface InstagramService {
    @POST("v1/accounts:signUp?key=AIzaSyCWU9zF-tfKOc4gTAxzdb3y2Td0URCAqfw")
    @JvmSuppressWildcards
    fun register(@Body body: Map<String, Any>): Call<RegisterModel>
}