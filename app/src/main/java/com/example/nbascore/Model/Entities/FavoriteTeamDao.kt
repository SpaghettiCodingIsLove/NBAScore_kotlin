package com.example.nbascore.Model.Entities

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.selects.select

@Dao
interface FavoriteTeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTeam: FavoriteTeam)

    @Delete
    suspend fun delete(favoriteTeam: FavoriteTeam)

    @Query("select * from favoriteTeam_table")
    fun getAllFavTeams() : LiveData<List<FavoriteTeam>>
}