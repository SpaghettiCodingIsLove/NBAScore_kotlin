package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.Stats
import com.example.nbascore.Model.Entities.StatsData
import com.example.nbascore.Model.Repositories.PlayerRepository
import com.example.nbascore.Model.Repositories.StatsRepository
import kotlinx.coroutines.launch

class StatsViewModel(application: Application): AndroidViewModel(application) {
    private var _allStats: MutableLiveData<ArrayList<Stats>> = MutableLiveData()
    val allStats: LiveData<ArrayList<Stats>>
        get()= _allStats

    private var _statsFromGame: MutableLiveData<ArrayList<Stats>> = MutableLiveData()
    val statsFromGame: LiveData<ArrayList<Stats>>
        get()= _statsFromGame

    fun getAllStats()
    {
        viewModelScope.launch {
            _allStats.value = StatsRepository.getAllStats()?.data
        }
    }

    fun getStatsFromGame(gameId: Long){
        viewModelScope.launch {
            _statsFromGame.value = StatsRepository.getStatsFromGame(gameId)?.data
        }
    }
}