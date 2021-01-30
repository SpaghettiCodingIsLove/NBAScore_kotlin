package com.example.nbascore.Model.Repositories

import com.example.nbascore.Model.API.Service
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.Model.Entities.GameData
import retrofit2.awaitResponse

class GameRepository {

    companion object {
        suspend fun getAllGames(): GameData?{
            return Service.gameApi.getAllGames().awaitResponse().body()
        }

        suspend fun getGamesByDate(startDate: String, endDate: String): GameData?{
            return  Service.gameApi.getGamesByDate(startDate, endDate).awaitResponse().body()
        }

        suspend fun getFavoriteTeamsGames(seasons: Int, teamId :Long) : GameData?{
            return Service.gameApi.getFavoriteTeamsGames(seasons, teamId).awaitResponse().body()
        }
    }
}