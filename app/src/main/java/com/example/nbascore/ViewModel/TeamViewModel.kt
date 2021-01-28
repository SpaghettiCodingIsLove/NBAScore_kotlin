package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.Team
import com.example.nbascore.Model.Entities.TeamData
import com.example.nbascore.Model.Repositories.PlayerRepository
import com.example.nbascore.Model.Repositories.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(application: Application): AndroidViewModel(application) {
    private var _allTeams: MutableLiveData<ArrayList<Team>> = MutableLiveData()
    val allTeams: LiveData<ArrayList<Team>>
        get() = _allTeams

    fun clear() {
        _allTeams = MutableLiveData()
    }

    fun getAllTeams()
    {
        viewModelScope.launch {
            _allTeams.value = TeamRepository.getAllTeams()?.data
        }
    }

    fun getInConference(conference: String)
    {
        viewModelScope.launch {
            var tmp = TeamRepository.getAllTeams()?.data
            var final: ArrayList<Team> = arrayListOf()
            tmp?.forEach {
                if (it.conference == conference) {
                    final.add(it)
                }
            }
            _allTeams.value = final
        }
    }

    fun getInDivision(division: String){
        viewModelScope.launch {
            var tmp = TeamRepository.getAllTeams()?.data
            var final: ArrayList<Team> = arrayListOf()
            tmp?.forEach {
                if (it.division == division) {
                    final.add(it)
                }
            }
            _allTeams.value = final
        }
    }

}