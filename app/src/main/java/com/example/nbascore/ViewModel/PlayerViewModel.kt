package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Repositories.PlayerRepository
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {
    private var _allPlayers: PlayerData? = null
    val allPlayers: PlayerData?
    get()= _allPlayers

    fun getAllPlayers()
    {
        viewModelScope.launch {
            _allPlayers = PlayerRepository.getAllPlayers()
        }
    }
}