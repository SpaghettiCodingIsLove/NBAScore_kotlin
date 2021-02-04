package com.example.nbascore.View

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
import com.example.nbascore.ViewModel.TeamAdapter
import com.example.nbascore.ViewModel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_table.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTable.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTable : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  lateinit var viewModel: TeamViewModel

    private lateinit var myAdapter: TeamAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

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

        viewModel = ViewModelProvider(requireActivity()).get(TeamViewModel::class.java)

        if (HelperClass.Conference != ""){
            if (HelperClass.Conference != HelperClass.PreviousConference){
                viewModel.clear()
                viewModel.getInConference(HelperClass.Conference)
                HelperClass.PreviousConference = HelperClass.Conference
                HelperClass.allTeamsLoaded = false
            }
        }
        else if (HelperClass.Division != ""){
            if (HelperClass.Division != HelperClass.PreviousDivision){
                viewModel.clear()
                viewModel.getInDivision(HelperClass.Division)
                HelperClass.PreviousDivision = HelperClass.Division
                HelperClass.allTeamsLoaded = false
            }
        }
        else {
            if (!HelperClass.allTeamsLoaded){
                viewModel.clear()
                viewModel.getAllTeams()
                HelperClass.allTeamsLoaded = true
            }
        }

        myAdapter = TeamAdapter(viewModel.allTeams, context)
        myLayoutManager = LinearLayoutManager(context)

        viewModel.allTeams.observe(viewLifecycleOwner, androidx.lifecycle.Observer { myAdapter.notifyDataSetChanged()
        progressBarTeams.visibility = View.INVISIBLE
        })

        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = teamsTable.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        if (HelperClass.Division != "") {
            tableName.text = HelperClass.Division
        }
        else if (HelperClass.Conference != "") {
            tableName.text = HelperClass.Conference
        }
        else {
            tableName.text = "NBA teams"
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTable.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTable().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}