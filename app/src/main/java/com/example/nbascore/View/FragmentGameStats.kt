package com.example.nbascore.View

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.R
import com.example.nbascore.ViewModel.StatsAdapter
import com.example.nbascore.ViewModel.StatsViewModel
import com.google.android.material.internal.ContextUtils
import kotlinx.android.synthetic.main.fragment_game_stats.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentGameStats.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentGameStats : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  lateinit var viewModel: StatsViewModel

    private lateinit var myAdapter: StatsAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider(requireActivity()).get(StatsViewModel::class.java)

        myLayoutManager = LinearLayoutManager(context)

        viewModel.getStatsFromGame(DataSource.selectedGame?.id!!)
        viewModel.getHomeStatsFromGame(DataSource.selectedGame?.id!!)
        viewModel.getAwayStatsFromGame(DataSource.selectedGame?.id!!)

        myAdapter = StatsAdapter(viewModel.statsFromGame, viewModel.homeStatsFromGame, viewModel.awayStatsFromGame)

        viewModel.statsFromGame.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        viewModel.homeStatsFromGame.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        viewModel.awayStatsFromGame.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_game_stats, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var gameTime = view.findViewById<TextView>(R.id.GameTime)
        var homeTeam = view.findViewById<TextView>(R.id.homeTeamName)
        var homeTeamImage = view.findViewById<ImageView>(R.id.homeTeam)
        var homeTeamScore = view.findViewById<TextView>(R.id.HomeTeamScore)
        var gameQuart = view.findViewById<TextView>(R.id.GameQuart)
        var visitorTeamScore = view.findViewById<TextView>(R.id.VisitorTeamScore)
        var visitorTeam = view.findViewById<TextView>(R.id.visitorTeamName)
        var visitorTeamImage = view.findViewById<ImageView>(R.id.VisitorTeam)
        var constraintLayoutGames = view.findViewById<ConstraintLayout>(R.id.constraintLayoutOneGame)
        var breakTV = view.findViewById<TextView>(R.id.Break)
        var home = view.findViewById<Button>(R.id.buttonHome)
        var away = view.findViewById<Button>(R.id.buttonAway)

        var game = DataSource.selectedGame

        homeTeam.text = game?.home_team?.name
        visitorTeam.text = game?.visitor_team?.name
        gameTime.text = game?.status
        homeTeamScore.text = game?.home_team_score.toString()
        visitorTeamScore.text = game?.visitor_team_score.toString()

        homeTeamImage.setImageDrawable(requireContext().resources.getDrawable(
                context?.resources?.getIdentifier(
                        game?.home_team?.abbreviation?.toLowerCase(), "drawable", ContextUtils.getActivity(
                        context
                )?.packageName)!!
        ))

        visitorTeamImage.setImageDrawable(requireContext().resources.getDrawable(
                context?.resources?.getIdentifier(
                        game?.visitor_team?.abbreviation?.toLowerCase(), "drawable", ContextUtils.getActivity(
                        context
                )?.packageName)!!
        ))

        if(game?.status == "Final")
        {
            gameQuart.text = "4Q 00:00"
        }
        else
        {
            if(game?.time == "")
                gameQuart.text = "1Q 12:00"
            else
                gameQuart.text = game?.time
        }

        if(game?.home_team_score!! > game?.visitor_team_score!!)
        {
            homeTeamScore.setTextColor(Color.parseColor("#c9082a"))
            visitorTeamScore.setTextColor(Color.WHITE)
        }
        else if(game?.home_team_score!! < game?.visitor_team_score!!)
        {
            visitorTeamScore.setTextColor(Color.parseColor("#c9082a"))
            homeTeamScore.setTextColor(Color.WHITE)
        }
        else
        {
            homeTeamScore.setTextColor(Color.WHITE)
            visitorTeamScore.setTextColor(Color.WHITE)
        }

        if(game?.postseason == false)
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

        home.setOnClickListener {

            myAdapter.stats = myAdapter.homeStats
            myAdapter.notifyDataSetChanged()

            Toast.makeText(context,"Home Team Players",Toast.LENGTH_SHORT).show()
        }

        away.setOnClickListener {
            myAdapter.stats = myAdapter.awayStats
            myAdapter.notifyDataSetChanged()

            Toast.makeText(context,"Away Team Players",Toast.LENGTH_SHORT).show()
        }

        recyclerView = statsRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentGameStats.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentGameStats().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}