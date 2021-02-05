package com.example.nbascore.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.R
import com.example.nbascore.ViewModel.GameViewModel
import com.example.nbascore.ViewModel.GamesAdapter
import com.example.nbascore.ViewModel.SeasonAveragesAdapter
import com.example.nbascore.ViewModel.SeasonAveragesViewModel
import kotlinx.android.synthetic.main.fragment_player_stats.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPlayerStats.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPlayerStats : Fragment(), AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var seasons: List<Int> = listOf(2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000, 1999, 1998, 1997,
        1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979
        )
    private var choosenSeason: Int = 2020

    private lateinit var viewModel: SeasonAveragesViewModel

    private lateinit var myAdapter: SeasonAveragesAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var SpinnerAdapter: ArrayAdapter<Int>

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

        viewModel = ViewModelProvider(requireActivity()).get(SeasonAveragesViewModel::class.java)

        viewModel.getPlayersSeasonAverages(DataSource.selectedPlayer?.id!!, choosenSeason)

        myLayoutManager = LinearLayoutManager(context)
        myAdapter = SeasonAveragesAdapter(viewModel.playersSeasonAverages)

        viewModel.playersSeasonAverages.observe(viewLifecycleOwner, Observer {
            myAdapter.notifyDataSetChanged()
            Log.d("TEST", "Pobrano: " + viewModel.playersSeasonAverages.value?.size)
        })

        SpinnerAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                seasons
            )
        }!!
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        return inflater.inflate(R.layout.fragment_player_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = seasonAveragesRV.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        SeasonSpinner.adapter = SpinnerAdapter
        SeasonSpinner.onItemSelectedListener = this

        choosenPlayerName.text = DataSource.selectedPlayer?.first_name + " " + DataSource.selectedPlayer?.last_name
        choosenPlayerPosition.text = DataSource.selectedPlayer?.position
        if(DataSource.selectedPlayer?.height_feet != null && DataSource.selectedPlayer?.height_inches != null)
            choosenPlayerHeight.text = DataSource.selectedPlayer?.heightCm.toString() + " cm"
        if(DataSource.selectedPlayer?.weight_pounds != null)
            choosenPlayerWeight.text = DataSource.selectedPlayer?.weightKg.toString() + "kg "
        choosenPlayerTeam.text = DataSource.selectedPlayer?.team?.name
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        choosenSeason = parent.getItemAtPosition(pos).toString().toInt()
        viewModel.getPlayersSeasonAverages(DataSource.selectedPlayer?.id!!, choosenSeason)
        Log.d("TEST", "Zmienono: ${DataSource.selectedPlayer?.id.toString()}, ${choosenSeason.toString()}")
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        choosenSeason = 2020
        viewModel.getPlayersSeasonAverages(DataSource.selectedPlayer?.id!!, choosenSeason)
        Log.d("TEST", "Zmienono: ${DataSource.selectedPlayer?.id.toString()}, ${choosenSeason.toString()}")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentPlayerStats.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentPlayerStats().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}