package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.SeasonAveragesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SeasonAveragesAPI {
    @GET("season_averages/")
    fun getAllSeasonAverages() : Call<SeasonAveragesData>

    @GET("season_averages/")
    fun getPlayersSeasonAverages(@Query("player_ids[]") playerId: Long, @Query("season") season: Int) : Call<SeasonAveragesData>
}