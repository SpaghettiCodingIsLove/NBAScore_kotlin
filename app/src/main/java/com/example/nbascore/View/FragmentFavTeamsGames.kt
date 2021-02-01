package com.example.nbascore.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.HelperClass
import com.example.nbascore.R
import com.example.nbascore.ViewModel.GameViewModel
import com.example.nbascore.ViewModel.GamesAdapter2
import com.google.android.material.internal.ContextUtils
import kotlinx.android.synthetic.main.fragment_fav_teams_games.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFavTeamsGames.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFavTeamsGames : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: GameViewModel

    private lateinit var myAdapter: GamesAdapter2
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    private val currentDate = Calendar.getInstance()
    private val currentMonth = currentDate[Calendar.MONTH]
    private val currentYear = currentDate[Calendar.YEAR]

    private var currentSeason: Int = 2020

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        if(currentMonth <= 8)
            currentSeason = currentYear-1
        else
            currentSeason = currentYear

        viewModel.getFavoriteTeamsGames(currentSeason, DataSource.currentFavTeamId)

        myLayoutManager = LinearLayoutManager(context)
        myAdapter = GamesAdapter2(viewModel.favoriteGames, context)

        viewModel.favoriteGames.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_fav_teams_games, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = favTeamsGamesRV.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        currentTeamName.text = DataSource.currentFavTeamName
        currentSeasonTV.text = "Season: $currentSeason/"+(currentSeason+1)
        currentTeamLogo.setImageDrawable(requireContext().resources.getDrawable(
            context?.resources?.getIdentifier(
                DataSource.currentTeamAbbreaviation.toLowerCase(), "drawable", ContextUtils.getActivity(
                context
            )?.packageName)!!
        ))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentFavTeamsGames.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFavTeamsGames().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}