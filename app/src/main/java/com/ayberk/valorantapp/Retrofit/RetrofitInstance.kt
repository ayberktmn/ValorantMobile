package com.ayberk.valorantapp.Retrofit

import com.ayberk.valorantapp.models.*
import retrofit2.http.GET

interface RetrofitInstance {

    @GET("v1/agents")
    fun getAgends():retrofit2.Call<Agends>

    @GET("v1/agents")
    fun getAbility():retrofit2.Call<Data>

    @GET("v1/weapons")
    fun getWeapons():retrofit2.Call<Weapons>

    @GET("v1/weapons/skins")
    fun getSkins():retrofit2.Call<com.ayberk.valorantapp.models.skin.Skin>

    @GET("v1/maps")
    fun getMaps():retrofit2.Call<com.ayberk.valorantapp.models.maps.Map>

}