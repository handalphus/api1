package com.example.api1.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.api1.model.Pollution
import com.example.api1.model.Value
import com.example.api1.model.repositories.PolutionDataRepository
import kotlinx.coroutines.*

class PollutionViewModel(application: Application):AndroidViewModel(application) {

//     var pollutions:Pollution = Pollution("123", listOf())
    lateinit var pollutionsLive : LiveData<Pollution>

    val repository:PolutionDataRepository
    init {
        repository = PolutionDataRepository()
        updatePollutions(92)

        }



     fun updatePollutions(id:Int){
        pollutionsLive= repository.getPollution(id)
        Log.v("zanieczyszczenia",pollutionsLive.value.toString())

    }

}