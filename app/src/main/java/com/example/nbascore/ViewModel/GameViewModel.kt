package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.Model.Entities.GameData
import com.example.nbascore.Model.Repositories.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private var _gamesByDate: MutableLiveData<ArrayList<Game>> = MutableLiveData()
    val gamesByDate: LiveData<ArrayList<Game>>
        get()= _gamesByDate

    private var _favoriteGames: MutableLiveData<ArrayList<Game>> = MutableLiveData()
    val favoriteGames: LiveData<ArrayList<Game>>
        get()= _favoriteGames

    fun getGamesByDate(startDate: String, endDate: String)
    {
        viewModelScope.launch {
            _gamesByDate.value = GameRepository.getGamesByDate(startDate, endDate)?.data
        }
    }

    fun getFavoriteTeamsGames(season: Int, teamId: Long){
        viewModelScope.launch {
            _favoriteGames.value = GameRepository.getFavoriteTeamsGames(season, teamId)?.data
        }
    }
}