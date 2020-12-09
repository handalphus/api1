package com.example.api1.model.repositories

import android.os.Build
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.liveData
import com.example.api1.model.ApiCommunication
import com.example.api1.model.Pollution
import com.example.api1.model.Value
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PolutionDataRepository() {


       suspend fun getPollution(id:Int):LiveData<Pollution>{
            val data = MutableLiveData<Pollution>()
            val response = api.getMeasurements(id).awaitResponse()
            data.value = response.body()

            return data



    }
    companion object{
        val base_url="https://api.gios.gov.pl/pjp-api/rest/"
        private val api = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiCommunication::class.java)


    }
}