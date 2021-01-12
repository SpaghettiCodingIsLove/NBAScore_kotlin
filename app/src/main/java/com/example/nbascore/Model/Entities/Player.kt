package com.example.nbascore.Model.Entities

data class Player(
    val id: Long,
    val first_name: String,
    val last_name: String,
    val position: String,
    val height_feet: Int?,
    val height_inches: Int?,
    val weight_pounds: Int?,
    val team: Team?,
    val team_id: Long?
    ){
}