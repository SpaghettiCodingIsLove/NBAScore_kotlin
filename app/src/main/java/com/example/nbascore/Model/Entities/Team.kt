package com.example.nbascore.Model.Entities

data class Team(
    val id: Long,
    val abbrevation: String,
    val city: String,
    val conference: String,
    val division: String,
    val full_name: String,
    val name: String
) {
}