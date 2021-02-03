package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.StatsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StatsAPI {
    @GET("stats/")
    fun getAllStats() : Call<StatsData>

    @GET("stats/")
    fun getStatsFromGame() : Call<StatsData>
}