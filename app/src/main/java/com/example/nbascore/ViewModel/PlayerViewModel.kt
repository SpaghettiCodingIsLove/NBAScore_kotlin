package com.example.nbascore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nbascore.Model.Entities.Player
import com.example.nbascore.Model.HelperClass
import com.example.nbascore.Model.Repositories.PlayerRepository
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {
    private var _allPlayers: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    private var _playersInTeam: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    val allPlayers: LiveData<ArrayList<Player>>
    get()= _allPlayers
    val PlayersInTeam: LiveData<ArrayList<Player>>
    get() = _playersInTeam

    fun getAllPlayers()
    {
        viewModelScope.launch {
            _allPlayers.value = PlayerRepository.getAllPlayers()?.data
        }
    }

    fun clear() {
        viewModelScope.launch {
            _playersInTeam = MutableLiveData()
        }
    }

    fun getFullPlayerList() {
        viewModelScope.launch {
            if (_allPlayers.value == null || _allPlayers.value?.count() == 0){
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

                tmp.sortBy { x -> x.first_name }
                _allPlayers.value = tmp
            }
        }
    }

    fun getPlayersInTeam(teamId: Long?) {
        viewModelScope.launch {
            HelperClass.AllowBack = false
            if (_allPlayers.value == null || _allPlayers.value?.count() == 0){
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

                tmp.sortBy { x -> x.first_name }
                _allPlayers.value = tmp
            }


            var final: ArrayList<Player> = arrayListOf()

            _allPlayers.value?.forEach {
                if (it.team?.id == teamId) {
                    final.add(it)
                }
            }

            final.sortBy { x -> x.first_name }
            _playersInTeam.value = final
            HelperClass.AllowBack = true
        }
    }
}