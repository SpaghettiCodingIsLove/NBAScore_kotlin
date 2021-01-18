package com.example.nbascore.Model.Entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class GameData(
        val data: ArrayList<Game>,
        val meta: Meta
) {
}