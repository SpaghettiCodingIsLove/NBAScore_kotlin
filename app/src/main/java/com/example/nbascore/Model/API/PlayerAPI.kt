package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.GameData
import com.example.nbascore.Model.Entities.PlayerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerAPI {
    @GET("players/")
    fun getAllPlayers() : Call<PlayerData>
}