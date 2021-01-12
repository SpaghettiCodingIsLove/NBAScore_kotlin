package com.example.nbascore.Model.Repositories

import com.example.nbascore.Model.API.Service
import com.example.nbascore.Model.Entities.PlayerData
import retrofit2.awaitResponse

class PlayerRepository {
    
    companion object{
        suspend fun getAllPlayers(): PlayerData?{
            return Service.playerApi.getAllPlayers().awaitResponse().body()
        }
    }
}