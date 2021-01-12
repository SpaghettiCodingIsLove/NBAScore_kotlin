package com.example.nbascore.Model.Entities

data class Game(
    val id: Long,
    val date: String,
    val home_team_score: Int,
    val visitor_team_score: Int,
    val season: Int,
    val period: Int,
    val status: String,
    val time: String,
    val postseason: Boolean,
    val home_team: Team?,
    val home_team_id: Long?,
    val visitor_team: Team?,
    val visitor_team_id: Long?
) {
}