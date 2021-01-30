package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.Model.Entities.GameData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GameAPI {

    @GET("games/")
    fun getAllGames() : Call<GameData>

    @GET("games/")
    fun getGamesByDate(@Query("start_date") startDate: String, @Query("end_date") endDate: String) : Call<GameData>

    @GET("games/")
    fun getFavoriteTeamsGames(@Query("seasons[]") season: Int, @Query("team_ids[]") teamId: Long) : Call<GameData>
}