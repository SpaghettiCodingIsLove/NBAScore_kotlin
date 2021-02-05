package com.example.nbascore.Model.Entities

import kotlin.math.roundToInt

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
    public val heightCm: Int?
    get() = if (height_feet != null && height_inches != null) ((height_feet * 30.48) + (height_inches * 2.54)).roundToInt().toInt() else null
    public val weightKg: Int?
    get() = if (weight_pounds != null) (weight_pounds * 0.45).roundToInt().toInt() else null
}