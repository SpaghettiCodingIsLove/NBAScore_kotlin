package com.example.nbascore.Model

import com.example.nbascore.Model.Entities.Game

class DataSource {
    companion object{
        var actual_day: String = ""
        var selectedDay: Int = 0
        var selectedMonth: Int = 0
        var selectedYear: Int = 0
        var selectedGame: Game? = null

        fun createDate(): String{
            return selectedYear.toString()+"-"+(selectedMonth+1).toString()+"-"+selectedDay.toString()
        }
    }
}