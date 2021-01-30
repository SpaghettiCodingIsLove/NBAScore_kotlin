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
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Day
import com.example.nbascore.Model.Entities.Game
import com.example.nbascore.R
import com.google.android.material.internal.ContextUtils

class GamesAdapter(var games: LiveData<ArrayList<Game>>, private val context: Context?): RecyclerView.Adapter<GamesAdapter.GamesHolder>()  {
    inner class GamesHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.games_one_row,parent,false)
        return GamesHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: GamesHolder, position: Int) {
        var gameTime = holder.itemView.findViewById<TextView>(R.id.GameTime)
        var homeTeam = holder.itemView.findViewById<TextView>(R.id.homeTeamName)
        var homeTeamImage = holder.itemView.findViewById<ImageView>(R.id.homeTeam)
        var homeTeamScore = holder.itemView.findViewById<TextView>(R.id.HomeTeamScore)
        var gameQuart = holder.itemView.findViewById<TextView>(R.id.GameQuart)
        var visitorTeamScore = holder.itemView.findViewById<TextView>(R.id.VisitorTeamScore)
        var visitorTeam = holder.itemView.findViewById<TextView>(R.id.visitorTeamName)
        var visitorTeamImage = holder.itemView.findViewById<ImageView>(R.id.VisitorTeam)
        var constraintLayoutGames = holder.itemView.findViewById<ConstraintLayout>(R.id.constraintLayoutOneRow)
        var breakTV = holder.itemView.findViewById<TextView>(R.id.Break)

        homeTeam.text = games.value?.get(position)?.home_team?.abbreviation
        visitorTeam.text = games.value?.get(position)?.visitor_team?.abbreviation
        gameTime.text = games.value?.get(position)?.status
        homeTeamScore.text = games.value?.get(position)?.home_team_score.toString()
        visitorTeamScore.text = games.value?.get(position)?.visitor_team_score.toString()

        homeTeamImage.setImageDrawable(context!!.resources.getDrawable( context.resources.getIdentifier(games.value?.get(position)?.home_team?.abbreviation?.toLowerCase(), "drawable", ContextUtils.getActivity(
            context
        )?.packageName)))

        visitorTeamImage.setImageDrawable(context!!.resources.getDrawable( context.resources.getIdentifier(games.value?.get(position)?.visitor_team?.abbreviation?.toLowerCase(), "drawable", ContextUtils.getActivity(
            context
        )?.packageName)))

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
            homeTeamScore.setTextColor(Color.parseColor("#c9082a"))
            visitorTeamScore.setTextColor(Color.WHITE)
        }
        else if(games.value?.get(position)?.home_team_score!! < games.value?.get(position)?.visitor_team_score!!)
        {
            visitorTeamScore.setTextColor(Color.parseColor("#c9082a"))
            homeTeamScore.setTextColor(Color.WHITE)
        }
        else
        {
            homeTeamScore.setTextColor(Color.WHITE)
            visitorTeamScore.setTextColor(Color.WHITE)
        }

        if(games.value?.get(position)?.postseason == false)
        {
            val blueColor = Color.parseColor("#17408b")
            gameTime.setBackgroundColor(blueColor)
            homeTeam.setBackgroundColor(blueColor)
            homeTeamScore.setBackgroundColor(blueColor)
            gameQuart.setBackgroundColor(blueColor)
            visitorTeamScore.setBackgroundColor(blueColor)
            visitorTeam.setBackgroundColor(blueColor)
            constraintLayoutGames.setBackgroundColor(blueColor)
            breakTV.setBackgroundColor(blueColor)
        }
        else
        {
            val redColor = Color.parseColor("#c9082a")
            gameTime.setBackgroundColor(redColor)
            homeTeam.setBackgroundColor(redColor)
            homeTeamScore.setBackgroundColor(redColor)
            gameQuart.setBackgroundColor(redColor)
            visitorTeamScore.setBackgroundColor(redColor)
            visitorTeam.setBackgroundColor(redColor)
            constraintLayoutGames.setBackgroundColor(redColor)
            breakTV.setBackgroundColor(redColor)
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