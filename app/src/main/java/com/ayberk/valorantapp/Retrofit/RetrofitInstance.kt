package com.ayberk.valorantapp.Retrofit

import com.ayberk.valorantapp.models.*
import com.ayberk.valorantapp.models.Agends.Agends
import com.ayberk.valorantapp.models.Agends.Data
import com.ayberk.valorantapp.models.Agends.Weapons
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

    @GET("v1/playercards")
    fun getCards():retrofit2.Call<com.ayberk.valorantapp.models.playerscard.Cards>

    @GET("v1/competitivetiers")
    fun getCompetitive():retrofit2.Call<com.ayberk.valorantapp.models.competitive.Competitive>

}