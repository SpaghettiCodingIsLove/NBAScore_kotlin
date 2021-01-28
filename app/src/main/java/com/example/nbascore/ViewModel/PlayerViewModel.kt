package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.Player
import com.example.nbascore.Model.Repositories.PlayerRepository
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {
    private var _allPlayers: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    val allPlayers: LiveData<ArrayList<Player>>
    get()= _allPlayers

    fun getAllPlayers()
    {
        viewModelScope.launch {
            _allPlayers.value = PlayerRepository.getAllPlayers()?.data
        }
    }

    fun clear() {
        viewModelScope.launch {
            _allPlayers = MutableLiveData()
        }
    }

    fun getFullPlayerList() {
        viewModelScope.launch {
            var currResponse = PlayerRepository.getPlayersPage(100, 1)
            var final: ArrayList<Player> = arrayListOf()
            if (currResponse != null) {
                final.addAll(currResponse.data)
            }
            while (currResponse?.meta?.next_page != null){
                currResponse = PlayerRepository.getPlayersPage(100, currResponse?.meta?.next_page!!)
                if (currResponse != null) {
                    final.addAll(currResponse.data)
                }
            }

            final.sortBy { x -> x.first_name }
            _allPlayers.value = final
        }
    }

    fun getPlayersInTeam(teamId: Long?) {
        viewModelScope.launch {
            var currResponse = PlayerRepository.getPlayersPage(100, 1)
            var tmp: ArrayList<Player> = arrayListOf()
            if (currResponse != null) {
                tmp.addAll(currResponse.data)
            }
            while (currResponse?.meta?.next_page != null){
                currResponse = PlayerRepository.getPlayersPage(100, currResponse?.meta?.next_page!!)
                if (currResponse != null) {
                    tmp.addAll(currResponse.data)
                }
            }

            var final: ArrayList<Player> = arrayListOf()

            tmp.forEach {
                if (it.team?.id == teamId) {
                    final.add(it)
                }
            }

            final.sortBy { x -> x.first_name }
            _allPlayers.value = final
        }
    }
}