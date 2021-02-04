package com.example.nbascore.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.Model.Entities.SeasonAverages
import com.example.nbascore.R
import com.google.android.material.internal.ContextUtils

class SeasonAveragesAdapter(var averages: LiveData<ArrayList<SeasonAverages>>): RecyclerView.Adapter<SeasonAveragesAdapter.SeasonAveragesHolder>()  {
    inner class SeasonAveragesHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonAveragesHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.season_averages_row,parent,false)
        return SeasonAveragesHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: SeasonAveragesHolder, position: Int) {
        var season = holder.itemView.findViewById<TextView>(R.id.seasonTV)
        var games = holder.itemView.findViewById<TextView>(R.id.gamesPlayedTV)
        var points = holder.itemView.findViewById<TextView>(R.id.pointsTV)
        var rebounds = holder.itemView.findViewById<TextView>(R.id.reboundsTV)
        var assists = holder.itemView.findViewById<TextView>(R.id.assistsTV)
        var steals = holder.itemView.findViewById<TextView>(R.id.stealsTV)
        var blocks = holder.itemView.findViewById<TextView>(R.id.blocksTV)
        var minutes = holder.itemView.findViewById<TextView>(R.id.minutesTV)

        season.text = "Season: " + averages.value?.get(position)?.season.toString() + "/" + (averages.value?.get(position)?.season!!+1).toString()
        games.text = averages.value?.get(position)?.games_played.toString()
        points.text = averages.value?.get(position)?.pts.toString()
        rebounds.text = averages.value?.get(position)?.reb.toString()
        assists.text = averages.value?.get(position)?.ast.toString()
        steals.text = averages.value?.get(position)?.stl.toString()
        blocks.text = averages.value?.get(position)?.blk.toString()
        minutes.text = averages.value?.get(position)?.min.toString()
    }

    override fun getItemCount(): Int {
        return averages.value?.size ?: 0
    }
}