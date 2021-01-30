package com.example.nbascore.Model.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="favoriteTeam_table")
data class FavoriteTeam(@PrimaryKey(autoGenerate = true)val id: Long,
     val teamId: Long,
     val abbreviation: String,
     val city: String,
     val conference: String,
     val division: String,
     val full_name: String,
     val name: String) {
}