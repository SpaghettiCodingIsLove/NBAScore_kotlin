package com.example.nbascore.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.Model.Entities.Team
import com.example.nbascore.Model.HelperClass
import com.example.nbascore.R
import com.google.android.material.internal.ContextUtils.getActivity

class TeamAdapter(private var teamList: LiveData<ArrayList<Team>>, private val context: Context?) : RecyclerView.Adapter<TeamAdapter.TeamHolder>() {
    inner class TeamHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.team_row,parent,false)
        return TeamHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: TeamAdapter.TeamHolder, position: Int) {
        var teamName = holder.itemView.findViewById<TextView>(R.id.teamName)
        var logo: ImageView = holder.itemView.findViewById(R.id.logo)

        teamName.text = teamList.value?.get(position)?.full_name
        logo.setImageDrawable(context!!.resources.getDrawable( context.resources.getIdentifier(teamList.value?.get(position)?.abbreviation?.toLowerCase(), "drawable", getActivity(context)?.packageName)))

        holder.itemView.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_fragmentTable_to_fragmentTeamPlayers)
            HelperClass.CurrTeam = teamList.value?.get(position)
        }
    }

    override fun getItemCount(): Int {
        return teamList.value?.size ?: 0
    }
}