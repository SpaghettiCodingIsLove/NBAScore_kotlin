package com.example.nbascore.ViewModel

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Day
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.R

class GamesAdapter(var games: ArrayList<Game>?): RecyclerView.Adapter<GamesAdapter.GamesHolder>()  {
    inner class GamesHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.games_one_row,parent,false)
        return GamesHolder(view)
    }

    override fun onBindViewHolder(holder: GamesHolder, position: Int) {
        var gameTime = holder.itemView.findViewById<TextView>(R.id.GameTime)
        var homeTeam = holder.itemView.findViewById<TextView>(R.id.homeTeam)
        var homeTeamScore = holder.itemView.findViewById<TextView>(R.id.HomeTeamScore)
        var gameQuart = holder.itemView.findViewById<TextView>(R.id.GameQuart)
        var visitorTeamScore = holder.itemView.findViewById<TextView>(R.id.VisitorTeamScore)
        var visitorTeam = holder.itemView.findViewById<TextView>(R.id.VisitorTeam)

        homeTeam.text = games?.get(position)?.home_team?.abbreviation
        visitorTeam.text = games?.get(position)?.visitor_team?.abbreviation
        gameTime.text = games?.get(position)?.status
        homeTeamScore.text = games?.get(position)?.home_team_score.toString()
        visitorTeamScore.text = games?.get(position)?.visitor_team_score.toString()

        if(games?.get(position)?.status == "FINAL")
        {
            gameQuart.text = "4Q 00:00"
        }
        else
        {
            gameQuart.text = games?.get(position)?.period.toString() + "Q " + games?.get(position)?.time
        }

        holder.itemView.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_fragmentGames_to_fragmentGameStats)
            DataSource.selectedGame = games?.get(position)
        }
    }

    override fun getItemCount(): Int {
        return games?.size?: 0
    }
}