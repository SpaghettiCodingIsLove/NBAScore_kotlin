package com.example.nbascore.Model.Entities

data class Stats(
    val id: Long,
    val ast: Int,
    val blk: Int,
    val dreb: Int,
    val fg3_pct: Double,
    val fg3a: Int,
    val fg3m: Int,
    val fg_pct: Double,
    val fga: Int,
    val fgm: Int,
    val ft_pcm: Double,
    val fta: Int,
    val ftm: Int,
    val game: Game,
    val min: String,
    val oreb: Int,
    val pf: Int,
    val player: Player,
    val pts: Int,
    val reb: Int,
    val stl: Int,
    val team: Team,
    val turnover: Int
) {
}