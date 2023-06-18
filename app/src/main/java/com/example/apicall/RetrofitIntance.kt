package com.example.apicall

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitIntance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
    val apiInterface by lazy{
        retrofit.create(ApiIterface:: class.java)

    }
}