package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.PlayerData
import com.example.nbascore.Model.Entities.SeasonAveragesData
import com.example.nbascore.Model.Repositories.PlayerRepository
import com.example.nbascore.Model.Repositories.SeasonAveragesRepository
import kotlinx.coroutines.launch

class SeasonAveragesViewModel(application: Application): AndroidViewModel(application) {
    private var _allSeasonAverages: SeasonAveragesData? = null
    val allSeasonAverages: SeasonAveragesData?
        get()= _allSeasonAverages

    fun getAllSeasonAverages()
    {
        viewModelScope.launch {
            _allSeasonAverages = SeasonAveragesRepository.getAllSeasonAverages()
        }
    }
}