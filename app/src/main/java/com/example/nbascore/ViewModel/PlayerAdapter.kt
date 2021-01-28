package com.example.nbascore.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.Entities.Player
import com.example.nbascore.R

class PlayerAdapter(private var playerList: LiveData<ArrayList<Player>>) : RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {
    inner class PlayerHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.player_team_row,parent,false)
        return PlayerHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerAdapter.PlayerHolder, position: Int) {
        var playerName = holder.itemView.findViewById<TextView>(R.id.playerName)
        var currPlayer = playerList.value?.get(position)
        playerName.text = currPlayer?.first_name + " " + currPlayer?.last_name
    }

    override fun getItemCount(): Int {
        return playerList.value?.size ?: 0
    }
}