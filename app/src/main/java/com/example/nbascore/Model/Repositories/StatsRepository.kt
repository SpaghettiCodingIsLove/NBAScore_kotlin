package com.example.nbascore.Model.Repositories

import com.example.nbascore.Model.API.Service
import com.example.nbascore.Model.Entities.GameData
import com.example.nbascore.Model.Entities.StatsData
import retrofit2.awaitResponse

class StatsRepository {
    companion object{
        suspend fun getAllStats(): StatsData?{
            return Service.statsApi.getAllStats().awaitResponse().body()
        }
    }
}