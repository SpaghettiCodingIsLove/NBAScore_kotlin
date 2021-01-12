package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.SeasonAveragesData
import retrofit2.Call
import retrofit2.http.GET

interface SeasonAveragesAPI {
    @GET("season_averages/")
    fun getAllSeasonAverages() : Call<SeasonAveragesData>
}