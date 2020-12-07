package com.example.api1.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCommunication {
    @GET("data/getData/{id}")
    fun getMeasurements(@Path("id") id:Int): Call<Pollution>


}