package com.example.nbascore.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.R
import com.example.nbascore.ViewModel.PlayerAdapter2
import com.example.nbascore.ViewModel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_players.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPlayers.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPlayers : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: PlayerViewModel

    private lateinit var myAdapter: PlayerAdapter2
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

        viewModel = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)
        viewModel.clear()

        viewModel.getFullPlayerList2()

        myAdapter = PlayerAdapter2(viewModel.allPlayers2)
        myLayoutManager = LinearLayoutManager(context)

        viewModel.allPlayers2.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                myAdapter.notifyDataSetChanged()
                progressBarPlayers.visibility = View.INVISIBLE
            })

        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = playersTable.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        searchButton.setOnClickListener {
            var searchingPhrase: String = searchET.getText().toString()

            if(!"".equals(searchingPhrase))
            {
                viewModel.getSearchedPlayers(searchingPhrase)
                Toast.makeText(context, "Wyszukano frazę: $searchingPhrase", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentPlayers.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPlayers().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}