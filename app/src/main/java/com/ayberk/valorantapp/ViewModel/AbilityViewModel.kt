package com.ayberk.valorantapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.valorantapp.Retrofit.AgendsRetrofit
import com.ayberk.valorantapp.models.Agends.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AbilityViewModel@Inject constructor(private val repo: AgendsRetrofit) : ViewModel() {

    var abilityList : MutableLiveData<Data>


    init {
        abilityList = MutableLiveData()
    }

    fun getObserverLiveAbilityData(): MutableLiveData<Data> {
        return abilityList
    }

    fun  loadAbility(page:String){
        repo.getAbility(abilityList)
    }

}