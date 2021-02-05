package com.example.nbascore.Model

import androidx.lifecycle.MutableLiveData
import com.example.nbascore.Model.Entities.Team


class HelperClass {
    companion object {
        var CurrTeam: Team? = null
        var Conference: String = ""
        var Division: String = ""
        var PreviousConference: String = ""
        var PreviousDivision: String = ""
        var AllowBack: MutableLiveData<Boolean> = MutableLiveData(true)
        var year: Int = -1
        var month: Int = -1
        var day: Int = -1
<<<<<<< Updated upstream
        var allTeamsLoaded: Boolean = false
=======
        var home: Boolean = false;
>>>>>>> Stashed changes
    }
}