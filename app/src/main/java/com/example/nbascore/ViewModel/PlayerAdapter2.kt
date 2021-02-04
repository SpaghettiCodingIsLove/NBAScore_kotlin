package com.example.nbascore.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Entities.Player
import com.example.nbascore.R

class PlayerAdapter2(private var playerList: LiveData<ArrayList<Player>>) : RecyclerView.Adapter<PlayerAdapter2.PlayerHolder2>() {
    inner class PlayerHolder2(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder2 {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.player_team_row,parent,false)
        return PlayerHolder2(view)
    }

    override fun onBindViewHolder(holder: PlayerAdapter2.PlayerHolder2, position: Int) {
        var playerName = holder.itemView.findViewById<TextView>(R.id.playerName)
        var currPlayer = playerList.value?.get(position)
        playerName.text = currPlayer?.first_name + " " + currPlayer?.last_name

        holder.itemView.setOnClickListener {
                view -> view.findNavController().navigate(R.id.action_fragmentPlayers_to_fragmentPlayerStats)
            DataSource.selectedPlayer = currPlayer
        }
    }

    override fun getItemCount(): Int {
        return playerList.value?.size ?: 0
    }
}