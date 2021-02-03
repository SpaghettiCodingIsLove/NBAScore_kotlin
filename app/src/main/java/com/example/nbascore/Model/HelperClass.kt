package com.example.nbascore.Model

import com.example.nbascore.Model.Entities.Team


class HelperClass {
    companion object {
        var CurrTeam: Team? = null
        var Conference: String = ""
        var Division: String = ""
        var PreviousConference: String = ""
        var PreviousDivision: String = ""
        var AllowBack: Boolean = true
    }
}