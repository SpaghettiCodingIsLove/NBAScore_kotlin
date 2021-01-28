package com.example.nbascore.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.HelperClass
import com.example.nbascore.R
import com.example.nbascore.ViewModel.PlayerAdapter
import com.example.nbascore.ViewModel.PlayerViewModel
import com.google.android.material.internal.ContextUtils
import kotlinx.android.synthetic.main.fragment_table.*
import kotlinx.android.synthetic.main.fragment_team_players.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTeamPlayers.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTeamPlayers : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  lateinit var viewModel: PlayerViewModel

    private lateinit var myAdapter: PlayerAdapter
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

        viewModel = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)
        viewModel.clear()
        viewModel.getPlayersInTeam(HelperClass.CurrTeam?.id)

        myAdapter = PlayerAdapter(viewModel.allPlayers)
        myLayoutManager = LinearLayoutManager(context)

        viewModel.allPlayers.observe(viewLifecycleOwner, androidx.lifecycle.Observer { myAdapter.notifyDataSetChanged()
            progressBar.visibility = View.INVISIBLE
        })

        return inflater.inflate(R.layout.fragment_team_players, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = teamPlayers.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        teamPic.setImageDrawable(requireContext().resources.getDrawable(
            context?.resources?.getIdentifier(HelperClass.CurrTeam?.abbreviation?.toLowerCase(), "drawable", ContextUtils.getActivity(
                context
            )?.packageName)!!
        ))
        teamNameBox.text = HelperClass.CurrTeam?.full_name
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTeamPlayers.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentTeamPlayers().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}