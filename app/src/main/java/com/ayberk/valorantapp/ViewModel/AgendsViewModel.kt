package com.ayberk.valorantapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.valorantapp.Retrofit.AgendsRetrofit
import com.ayberk.valorantapp.models.Ability
import com.ayberk.valorantapp.models.Agends
import com.ayberk.valorantapp.models.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgendsViewModel@Inject constructor(private val repo: AgendsRetrofit) : ViewModel() {

    var agendsList: MutableLiveData<Agends>



    init {
        agendsList = MutableLiveData()

    }

    fun getObserverLiveData(): MutableLiveData<Agends> {
        return agendsList
    }

    fun  loadAgends(page:String){

        repo.getAgends(agendsList)

    }
}