package com.example.apicall

import retrofit2.Call
import retrofit2.http.GET

interface ApiIterface {
    @GET("api/breeds/image/random")
    fun getData(): Call<ResponseDataClass>

}