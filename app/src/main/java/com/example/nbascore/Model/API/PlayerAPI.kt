package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.PlayerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerAPI {
    @GET("players/")
    fun getAllPlayers() : Call<PlayerData>

    @GET("players/")
    fun getPlayersPage(@Query("per_page") perPage: Int, @Query("page") page: Int) : Call<PlayerData>
}