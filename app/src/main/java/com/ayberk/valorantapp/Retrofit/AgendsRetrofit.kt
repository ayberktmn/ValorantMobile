package com.ayberk.valorantapp.Retrofit

import androidx.lifecycle.MutableLiveData
import com.ayberk.valorantapp.models.Ability
import com.ayberk.valorantapp.models.Agends
import com.ayberk.valorantapp.models.Data
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
}