package com.example.nbascore.Model.Repositories

import com.example.nbascore.Model.API.Service
import com.example.nbascore.Model.Entities.TeamData
import retrofit2.awaitResponse

class TeamRepository {
    companion object{
        suspend fun getAllTeams(): TeamData?{
            return Service.TeamApi.getAllTeams().awaitResponse().body()
        }
    }
}