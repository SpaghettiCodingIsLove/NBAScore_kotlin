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
    private var _allGames: GameData? = null
    var allGames: GameData? = null
    get()= _allGames

    private var _gamesByDate: GameData? = null
    var gamesByDate: GameData? = null
        get()= _gamesByDate

    fun getAllGames()
    {
        viewModelScope.launch {
            _allGames = GameRepository.getAllGames()
        }
    }

    fun getGamesByDate(startDate: String, endDate: String)
    {
        viewModelScope.launch {
            _gamesByDate = GameRepository.getGamesByDate(startDate, endDate)
        }
    }
}