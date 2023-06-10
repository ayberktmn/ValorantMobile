package com.ayberk.valorantapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.valorantapp.Retrofit.AgendsRetrofit
import com.ayberk.valorantapp.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgendsViewModel@Inject constructor(private val repo: AgendsRetrofit) : ViewModel() {

    var agendsList: MutableLiveData<Agends>
    var weaponsList : MutableLiveData<Weapons>
    var weaponsSkinList : MutableLiveData<com.ayberk.valorantapp.models.skin.Skin>
    var mapsList : MutableLiveData<com.ayberk.valorantapp.models.maps.Map>


    init {
        agendsList = MutableLiveData()
        weaponsList = MutableLiveData()
        weaponsSkinList = MutableLiveData()
        mapsList = MutableLiveData()
    }

    fun getObserverLiveData(): MutableLiveData<Agends> {
        return agendsList
    }

    fun  loadAgends(page:String){
        repo.getAgends(agendsList)
    }

    fun getObserverLiveDataWeapons(): MutableLiveData<Weapons> {
        return weaponsList
    }

    fun  loadWeapons(page:String){
        repo.getWeapons(weaponsList)
    }

    fun getWeaponSkin(): MutableLiveData<com.ayberk.valorantapp.models.skin.Skin> {
        return weaponsSkinList
    }

    fun  loadWeaponsSkin(page:String){
        repo.getWeaponStats(weaponsSkinList)
    }

    fun getMaps(): MutableLiveData<com.ayberk.valorantapp.models.maps.Map> {
        return mapsList
    }

    fun  loadMaps(page:String){
        repo.getMaps(mapsList)
    }
}