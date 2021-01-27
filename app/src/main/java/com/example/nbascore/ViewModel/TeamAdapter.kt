package com.example.nbascore.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.Model.Entities.Team
import com.example.nbascore.R

class TeamAdapter(private var teamList: LiveData<ArrayList<Team>>) : RecyclerView.Adapter<TeamAdapter.TeamHolder>() {
    inner class TeamHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.team_row,parent,false)
        return TeamHolder(view)
    }

    override fun onBindViewHolder(holder: TeamAdapter.TeamHolder, position: Int) {
        var teamName = holder.itemView.findViewById<TextView>(R.id.teamName)

        teamName.text = teamList.value?.get(position)?.full_name
    }

    override fun getItemCount(): Int {
        return teamList.value?.size ?: 0
    }
}