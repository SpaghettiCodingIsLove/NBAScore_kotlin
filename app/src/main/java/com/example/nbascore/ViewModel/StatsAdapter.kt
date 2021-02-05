package com.example.nbascore.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Entities.Stats
import com.example.nbascore.Model.HelperClass
import com.example.nbascore.R
import com.google.android.material.internal.ContextUtils.*
import java.lang.Exception

class StatsAdapter(var allStats: LiveData<ArrayList<Stats>>, var homeStats: LiveData<ArrayList<Stats>>, var awayStats: LiveData<ArrayList<Stats>>) : RecyclerView.Adapter<StatsAdapter.StatsHolder>() {
    inner class StatsHolder(view: View): RecyclerView.ViewHolder(view)

    var stats = allStats

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.StatsHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.player_stats_row,parent,false)
        return StatsHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: StatsAdapter.StatsHolder, position: Int) {
        var name = holder.itemView.findViewById<TextView>(R.id.playerStatsName)
        var pos = holder.itemView.findViewById<TextView>(R.id.playerPosition)
        var gametime = holder.itemView.findViewById<TextView>(R.id.playerGameTime)
        var points = holder.itemView.findViewById<TextView>(R.id.playerPoints)
        var rebounds = holder.itemView.findViewById<TextView>(R.id.playerRebounds)
        var assists = holder.itemView.findViewById<TextView>(R.id.playerAssists)

        name.text = stats.value?.get(position)?.player?.last_name
        pos.text = stats.value?.get(position)?.player?.position
        gametime.text = stats.value?.get(position)?.min
        rebounds.text = stats.value?.get(position)?.reb.toString()
        points.text = stats.value?.get(position)?.pts.toString()
        assists.text = stats.value?.get(position)?.ast.toString()

        Log.d("TEST", stats.value?.get(position)?.reb.toString() + " " + stats.value?.get(position)?.pts.toString())
    }

    override fun getItemCount(): Int {
        return stats.value?.size ?: 0
    }
}