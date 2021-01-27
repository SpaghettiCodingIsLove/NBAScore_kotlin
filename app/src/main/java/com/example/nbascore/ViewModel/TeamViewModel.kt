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
        get()= _allTeams

    fun getAllTeams()
    {
        viewModelScope.launch {
            _allTeams.value = TeamRepository.getAllTeams()?.data
        }
    }

}