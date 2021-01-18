package com.example.nbascore.Model

class DataSource {
    companion object{
        var actual_day: String = ""
        var selectedDay: Int = 0
        var selectedMonth: Int = 0
        var selectedYear: Int = 0

        fun createDate(): String{
            return "$selectedYear-$selectedMonth-$selectedDay"
        }
    }
}