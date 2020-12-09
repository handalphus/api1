package com.example.api1.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.api1.model.Pollution
import com.example.api1.model.Value
import com.example.api1.model.repositories.PolutionDataRepository
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates

class PollutionViewModel(application: Application):AndroidViewModel(application) {

//     var pollutions:Pollution = Pollution("123", listOf())
    private val _pollutionsMut=MutableLiveData<List<Value>>()
    val pollutionsLive : LiveData<List<Value>>
        get() {

            return  _pollutionsMut
        }
    private val _nameOfPollution = MutableLiveData<String>()

    val nameOfPolution: LiveData<String>
    get() = _nameOfPollution

    val repository:PolutionDataRepository
    init {
        repository = PolutionDataRepository()
        updatePollutions(92)

        }



     fun updatePollutions(id:Int){

         viewModelScope.launch {

             val polutions  =repository.getPollution(id)
             _pollutionsMut.value = polutions.value?.values
             _nameOfPollution.value = polutions.value?.key
             Log.v("zanieczyszczenia",_pollutionsMut.value.toString())
//             nameOfPolution=  _pollutionsMut.value?.key?:""
         }

    }

}