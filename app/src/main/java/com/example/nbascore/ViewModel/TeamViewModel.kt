package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.TeamData
import com.example.nbascore.Model.Repositories.PlayerRepository
import com.example.nbascore.Model.Repositories.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(application: Application): AndroidViewModel(application) {
    private var _allTeams: TeamData? = null
    val allTeams: TeamData?
        get()= _allTeams

    fun getAllTeams()
    {
        viewModelScope.launch {
            _allTeams = TeamRepository.getAllTeams()
        }
    }

}