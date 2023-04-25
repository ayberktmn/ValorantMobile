package com.ayberk.valorantapp.Retrofit

import com.ayberk.valorantapp.models.Ability
import com.ayberk.valorantapp.models.Agends
import com.ayberk.valorantapp.models.Data
import retrofit2.http.GET

interface RetrofitInstance {

    @GET("v1/agents")
    fun getAgends():retrofit2.Call<Agends>

    @GET("v1/agents")
    fun getAbility():retrofit2.Call<Data>

}