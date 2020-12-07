package com.example.api1.model.repositories

import android.os.Build
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.api1.model.ApiCommunication
import com.example.api1.model.Pollution
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PolutionDataRepository() {
    private val api = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiCommunication::class.java)
  var cachedData= Pollution("", listOf())

     fun getPollution(id:Int):LiveData<Pollution>{
        val data = MutableLiveData<Pollution>()
         api.getMeasurements(id).enqueue(object: Callback<Pollution>{
             override fun onResponse(call: Call<Pollution>, response: Response<Pollution>) {
                 Log.v("połączono z api",data.value.toString())
                cachedData = response.body()!!
                 data.value = response.body()
                 Log.v("połączono z api",data.value.toString())
             }

             override fun onFailure(call: Call<Pollution>, t: Throwable) {

             }

         })
         Log.v("z api otrzymano",data.value.toString())
         if (data.value==null){
             data.value=cachedData
         }

         return data

    }
    companion object{
        val base_url="https://api.gios.gov.pl/pjp-api/rest/"


    }
}