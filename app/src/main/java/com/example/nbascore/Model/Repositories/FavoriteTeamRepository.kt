package com.example.nbascore.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.nbascore.Model.Entities.FavoriteTeam
import com.example.nbascore.Model.Entities.FavoriteTeamDao

class FavoriteTeamRepository(val favoriteTeamDao: FavoriteTeamDao) {

    suspend fun insert(favoriteTeam: FavoriteTeam){
        favoriteTeamDao.insert(favoriteTeam)
    }

    suspend fun delete(favoriteTeam: FavoriteTeam){
        favoriteTeamDao.delete(favoriteTeam)
    }

    fun getAllFavTeams() : LiveData<List<FavoriteTeam>> {
        return favoriteTeamDao.getAllFavTeams()
    }

}