package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.StatsData
import com.example.nbascore.Model.Repositories.PlayerRepository
import com.example.nbascore.Model.Repositories.StatsRepository
import kotlinx.coroutines.launch

class StatsViewModel(application: Application): AndroidViewModel(application) {
    private var _allStats: StatsData? = null
    val allStats: StatsData?
        get()= _allStats

    fun getAllStats()
    {
        viewModelScope.launch {
            _allStats = StatsRepository.getAllStats()
        }
    }

    fun getStatsFromGame(id: Long)
    {
        viewModelScope.launch {
            //_statsFromGame.value = StatsRepository.getStatsFromGame(id)?.data
        }
    }
}