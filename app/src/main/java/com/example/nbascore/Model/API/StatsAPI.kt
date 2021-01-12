package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.StatsData
import retrofit2.Call
import retrofit2.http.GET

interface StatsAPI {
    @GET("stats/")
    fun getAllStats() : Call<StatsData>
}