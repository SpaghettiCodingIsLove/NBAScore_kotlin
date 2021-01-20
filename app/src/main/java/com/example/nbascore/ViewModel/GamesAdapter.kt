package com.example.nbascore.ViewModel

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Day
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.R

class GamesAdapter(var games: LiveData<ArrayList<Game>>): RecyclerView.Adapter<GamesAdapter.GamesHolder>()  {
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
        var constraintLayoutGames = holder.itemView.findViewById<ConstraintLayout>(R.id.constraintLayoutOneRow)
        var breakTV = holder.itemView.findViewById<TextView>(R.id.Break)

        homeTeam.text = games.value?.get(position)?.home_team?.abbreviation
        visitorTeam.text = games.value?.get(position)?.visitor_team?.abbreviation
        gameTime.text = games.value?.get(position)?.status
        homeTeamScore.text = games.value?.get(position)?.home_team_score.toString()
        visitorTeamScore.text = games.value?.get(position)?.visitor_team_score.toString()

        if(games.value?.get(position)?.status == "Final")
        {
            gameQuart.text = "4Q 00:00"
        }
        else
        {
            if(games.value?.get(position)?.time == "")
                gameQuart.text = "0Q 12:00"
            else
                gameQuart.text = games.value?.get(position)?.time
        }

        if(games.value?.get(position)?.home_team_score!! > games.value?.get(position)?.visitor_team_score!!)
        {
            homeTeamScore.setTextColor(Color.BLACK)
        }
        else if(games.value?.get(position)?.home_team_score!! < games.value?.get(position)?.visitor_team_score!!)
        {
            visitorTeamScore.setTextColor(Color.BLACK)
        }
        else
        {
            homeTeamScore.setTextColor(Color.WHITE)
            visitorTeamScore.setTextColor(Color.WHITE)
        }

        if(games.value?.get(position)?.postseason == false)
        {
            gameTime.setBackgroundColor(Color.parseColor("#3CABF5"))
            homeTeam.setBackgroundColor(Color.parseColor("#3CABF5"))
            homeTeamScore.setBackgroundColor(Color.parseColor("#3CABF5"))
            gameQuart.setBackgroundColor(Color.parseColor("#3CABF5"))
            visitorTeamScore.setBackgroundColor(Color.parseColor("#3CABF5"))
            visitorTeam.setBackgroundColor(Color.parseColor("#3CABF5"))
            constraintLayoutGames.setBackgroundColor(Color.parseColor("#3CABF5"))
            breakTV.setBackgroundColor(Color.parseColor("#3CABF5"))
        }
        else
        {
            gameTime.setBackgroundColor(Color.parseColor("#F55A6C"))
            homeTeam.setBackgroundColor(Color.parseColor("#F55A6C"))
            homeTeamScore.setBackgroundColor(Color.parseColor("#F55A6C"))
            gameQuart.setBackgroundColor(Color.parseColor("#F55A6C"))
            visitorTeamScore.setBackgroundColor(Color.parseColor("#F55A6C"))
            visitorTeam.setBackgroundColor(Color.parseColor("#F55A6C"))
            constraintLayoutGames.setBackgroundColor(Color.parseColor("#F55A6C"))
            breakTV.setBackgroundColor(Color.parseColor("#F55A6C"))
        }

        holder.itemView.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_fragmentGames_to_fragmentGameStats)
            DataSource.selectedGame = games.value?.get(position)
        }
    }

    override fun getItemCount(): Int {
        return games.value?.size ?: 0
    }
}