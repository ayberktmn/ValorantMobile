package com.ayberk.valorantapp.Retrofit

import androidx.lifecycle.MutableLiveData
import com.ayberk.valorantapp.models.Agends.Agends
import com.ayberk.valorantapp.models.Agends.Data
import com.ayberk.valorantapp.models.Agends.Weapons
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class AgendsRetrofit @Inject constructor(private val retroService: RetrofitInstance) {

    fun getAgends(liveData: MutableLiveData<Agends>){
        retroService.getAgends().enqueue(object : retrofit2.Callback<Agends>{
            override fun onResponse(call: Call<Agends>, response: Response<Agends>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Agends>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getAbility(liveData: MutableLiveData<Data>){
        retroService.getAbility().enqueue(object : retrofit2.Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getWeapons(liveData: MutableLiveData<Weapons>){
        retroService.getWeapons().enqueue(object : retrofit2.Callback<Weapons>{
            override fun onResponse(call: Call<Weapons>, response: Response<Weapons>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Weapons>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getWeaponStats(liveData: MutableLiveData<com.ayberk.valorantapp.models.skin.Skin>){
        retroService.getSkins().enqueue(object : retrofit2.Callback<com.ayberk.valorantapp.models.skin.Skin>{
            override fun onResponse(call: Call<com.ayberk.valorantapp.models.skin.Skin>, response: Response<com.ayberk.valorantapp.models.skin.Skin>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<com.ayberk.valorantapp.models.skin.Skin>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getMaps(liveData: MutableLiveData<com.ayberk.valorantapp.models.maps.Map>){
        retroService.getMaps().enqueue(object : retrofit2.Callback<com.ayberk.valorantapp.models.maps.Map>{
            override fun onResponse(call: Call<com.ayberk.valorantapp.models.maps.Map>, response: Response<com.ayberk.valorantapp.models.maps.Map>) {
                liveData.postValue(response.body())

            }

            override fun onFailure(call: Call<com.ayberk.valorantapp.models.maps.Map>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun getCards(liveData: MutableLiveData<com.ayberk.valorantapp.models.playerscard.Cards>){
        retroService.getCards().enqueue(object : retrofit2.Callback<com.ayberk.valorantapp.models.playerscard.Cards>{
            override fun onResponse(call: Call<com.ayberk.valorantapp.models.playerscard.Cards>, response: Response<com.ayberk.valorantapp.models.playerscard.Cards>) {
                liveData.postValue(response.body())

            }

            override fun onFailure(call: Call<com.ayberk.valorantapp.models.playerscard.Cards>, t: Throwable) {
                liveData.postValue(null)

            }
        })
    }

    fun getCompetitive(liveData: MutableLiveData<com.ayberk.valorantapp.models.competitive.Competitive>){
        retroService.getCompetitive().enqueue(object : retrofit2.Callback<com.ayberk.valorantapp.models.competitive.Competitive>{
            override fun onResponse(call: Call<com.ayberk.valorantapp.models.competitive.Competitive>, response: Response<com.ayberk.valorantapp.models.competitive.Competitive>) {
                liveData.postValue(response.body())


            }

            override fun onFailure(call: Call<com.ayberk.valorantapp.models.competitive.Competitive>, t: Throwable) {
                liveData.postValue(null)


            }
        })
    }

}