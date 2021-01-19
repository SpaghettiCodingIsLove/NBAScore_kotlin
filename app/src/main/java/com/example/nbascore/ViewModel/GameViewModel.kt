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

    fun getGamesByDate(startDate: String, endDate: String)
    {
        viewModelScope.launch {
            _gamesByDate.value = GameRepository.getGamesByDate(startDate, endDate)?.data
        }
    }
}