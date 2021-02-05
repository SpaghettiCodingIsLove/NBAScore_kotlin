package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.DataSource
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

    private var _homeStatsFromGame: MutableLiveData<ArrayList<Stats>> = MutableLiveData()
    val homeStatsFromGame: LiveData<ArrayList<Stats>>
        get()= _homeStatsFromGame

    private var _awayStatsFromGame: MutableLiveData<ArrayList<Stats>> = MutableLiveData()
    val awayStatsFromGame: LiveData<ArrayList<Stats>>
        get()= _awayStatsFromGame

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

    fun getHomeStatsFromGame(gameId: Long){
        viewModelScope.launch {
            var list = ArrayList<Stats>()
            for(i in StatsRepository.getStatsFromGame(gameId)?.data!!)
            {
                if(DataSource.selectedGame?.home_team?.id == i.team.id)
                    list.add(i)
            }

            list.sortByDescending { x -> x.pts }

            _homeStatsFromGame.value = list
        }
    }

    fun getAwayStatsFromGame(gameId: Long){
        viewModelScope.launch {
            var list = ArrayList<Stats>()
            for(i in StatsRepository.getStatsFromGame(gameId)?.data!!)
            {
                if(DataSource.selectedGame?.visitor_team?.id == i.team.id)
                    list.add(i)
            }

            list.sortByDescending { x -> x.pts }

            _awayStatsFromGame.value = list
        }
    }
}