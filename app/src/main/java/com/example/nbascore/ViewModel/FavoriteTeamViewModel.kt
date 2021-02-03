package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.FavoriteTeam
import com.example.nbascore.Model.HelperClass
import com.example.nbascore.Model.NBADatabase
import com.example.nbascore.Model.Repositories.FavoriteTeamRepository
import kotlinx.coroutines.launch

class FavoriteTeamViewModel(application: Application): AndroidViewModel(application) {
    val favoriteTeamRepository: FavoriteTeamRepository
    var teams: LiveData<List<FavoriteTeam>>

    init{
        favoriteTeamRepository = FavoriteTeamRepository(NBADatabase.getDatabase(application).favoriteTeamDao())
        teams = NBADatabase.getDatabase(application).favoriteTeamDao().getAllFavTeams()
    }

    fun addTeam(teamId: Long, abbreviation: String, city: String, conference: String, division: String, full_name: String, name: String){
        viewModelScope.launch {
            favoriteTeamRepository.insert(FavoriteTeam(id = 0, teamId = teamId, abbreviation = abbreviation, city = city, conference = conference, division = division, full_name = full_name, name = name))
        }
    }

    fun deleteTeam(favoriteTeam: FavoriteTeam){
        viewModelScope.launch {
            favoriteTeamRepository.delete(favoriteTeam)
        }
    }

    fun isFavorite(id: Long) :Boolean{
        var team = favoriteTeamRepository.getTeam(id)
        if (team == null || team.count() == 0){
            return false
        }
        return true
    }
}