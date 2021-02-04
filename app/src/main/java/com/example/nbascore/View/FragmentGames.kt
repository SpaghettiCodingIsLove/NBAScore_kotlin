package com.example.nbascore.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Day
import com.example.nbascore.Model.HelperClass
import com.example.nbascore.R
import com.example.nbascore.ViewModel.CalendarAdapter
import com.example.nbascore.ViewModel.GameViewModel
import com.example.nbascore.ViewModel.GamesAdapter
import kotlinx.android.synthetic.main.fragment_games.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.contracts.contract

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentGames.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentGames : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: GameViewModel

    private lateinit var calendarDays: ArrayList<Day>
    private lateinit var myAdapter: CalendarAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var myAdapter2: GamesAdapter
    private lateinit var myLayoutManager2: LinearLayoutManager
    private lateinit var recyclerView2: RecyclerView

    var cal = Calendar.getInstance()
    private val currentDate = Calendar.getInstance()
    private var currentDay = currentDate[Calendar.DAY_OF_MONTH]
    private var currentMonth = currentDate[Calendar.MONTH]
    private var currentYear = currentDate[Calendar.YEAR]
    private val sdf = SimpleDateFormat("MMMM yyyy")

    private fun getDaysInMonth(): ArrayList<Day>{
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        val dates = ArrayList<Date>()

        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

        while(dates.size < maxDaysInMonth){
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        var dni: ArrayList<Day> = arrayListOf()
        for (item in dates)
        {
            var kalendarz = Calendar.getInstance()
            kalendarz.time = item
            var isEnabled: Boolean = false
            if(kalendarz[Calendar.MONTH] == DataSource.selectedMonth && kalendarz[Calendar.DAY_OF_MONTH] == DataSource.selectedDay && kalendarz[Calendar.YEAR] == DataSource.selectedYear)
                isEnabled = true

            dni.add(Day(kalendarz[Calendar.DAY_OF_MONTH], kalendarz[Calendar.MONTH], kalendarz[Calendar.YEAR], kalendarz[Calendar.DAY_OF_WEEK], isEnabled))
        }

        return dni
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(calendarRecyclerView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        if (HelperClass.day != -1){
            currentDay = HelperClass.day
            currentMonth = HelperClass.month
            currentYear = HelperClass.year
        }

        DataSource.selectedDay = currentDay
        DataSource.selectedMonth = currentMonth
        DataSource.selectedYear = currentYear

        viewModel.getGamesByDate(DataSource.createDate(), DataSource.createDate())

        calendarDays = getDaysInMonth()

        myLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        myAdapter = CalendarAdapter(calendarDays, viewModel)

        myLayoutManager2 = LinearLayoutManager(context)
        myAdapter2 = GamesAdapter(viewModel.gamesByDate, context)

        viewModel.gamesByDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer { myAdapter2.notifyDataSetChanged() })

        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = calendarRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        recyclerView2 = gamesRecyclerView.apply {
            this.layoutManager = myLayoutManager2
            this.adapter = myAdapter2
        }

        calendarRecyclerView.scrollToPosition(DataSource.selectedDay-3)

        calendar_prev_button.setOnClickListener {

            cal.add(Calendar.MONTH, -1)
            calendarDays = getDaysInMonth()
            calendar_CurrentMonth.text = sdf.format(cal.time)
            myAdapter.days = calendarDays
            myAdapter.notifyDataSetChanged()
        }

        calendar_next_button.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            calendarDays = getDaysInMonth()
            calendar_CurrentMonth.text = sdf.format(cal.time)
            myAdapter.days = calendarDays
            myAdapter.notifyDataSetChanged()
        }
        calendar_CurrentMonth.text = sdf.format(cal.time)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentGames.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentGames().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}