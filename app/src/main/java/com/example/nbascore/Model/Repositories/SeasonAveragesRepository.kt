package com.example.nbascore.Model.Repositories

import com.example.nbascore.Model.API.Service
import com.example.nbascore.Model.Entities.SeasonAveragesData
import retrofit2.awaitResponse

class SeasonAveragesRepository {
    companion object{
        suspend fun getAllSeasonAverages(): SeasonAveragesData?{
            return Service.SeasonAveragesApi.getAllSeasonAverages().awaitResponse().body()
        }
    }
}