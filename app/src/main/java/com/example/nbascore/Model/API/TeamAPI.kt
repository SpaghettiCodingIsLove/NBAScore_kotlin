package com.example.nbascore.Model.API

import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.TeamData
import retrofit2.Call
import retrofit2.http.GET

interface TeamAPI {
    @GET("teams/")
    fun getAllTeams() : Call<TeamData>
}