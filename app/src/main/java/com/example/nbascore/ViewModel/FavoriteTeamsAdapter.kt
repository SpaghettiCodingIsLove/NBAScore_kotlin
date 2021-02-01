package com.example.nbascore.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
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
import com.example.nbascore.Model.Entities.FavoriteTeam
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.R
import com.google.android.material.internal.ContextUtils

class FavoriteTeamsAdapter(var favTeams: LiveData<List<FavoriteTeam>>, private val context: Context?, val viewModel: FavoriteTeamViewModel): RecyclerView.Adapter<FavoriteTeamsAdapter.FavTeamsHolder>()  {
    inner class FavTeamsHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamsHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.fav_team_one_row, parent,false)
        return FavTeamsHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: FavTeamsHolder, position: Int) {
        var logo = holder.itemView.findViewById<ImageView>(R.id.FavTeamLogo)
        var name = holder.itemView.findViewById<TextView>(R.id.favTeamName)
        var conference = holder.itemView.findViewById<TextView>(R.id.favtTeamConference)

        logo.setImageDrawable(context!!.resources.getDrawable( context.resources.getIdentifier(favTeams.value?.get(position)?.abbreviation?.toLowerCase(), "drawable", ContextUtils.getActivity(
            context
        )?.packageName)))

        name.text = favTeams.value?.get(position)?.full_name
        conference.text = "Conference: ${favTeams.value?.get(position)?.conference}"

        holder.itemView.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_fragmentFavoriteTeams_to_fragmentFavTeamsGames)
            DataSource.currentFavTeamId = favTeams.value?.get(position)?.teamId!!
            DataSource.currentFavTeamName = favTeams.value?.get(position)?.full_name!!
            DataSource.currentTeamAbbreaviation = favTeams.value?.get(position)?.abbreviation!!
        }
    }

    fun removeAt(position: Int){
        viewModel.deleteTeam(favTeams.value?.get(position)!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return favTeams.value?.size ?: 0
    }
}