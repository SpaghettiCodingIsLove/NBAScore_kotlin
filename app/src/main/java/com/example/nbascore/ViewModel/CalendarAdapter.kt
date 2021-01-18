package com.example.nbascore.ViewModel

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nbascore.Model.DataSource
import com.example.nbascore.Model.Day
import com.example.nbascore.R
import kotlinx.android.synthetic.main.custom_calendar_day.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(var days: ArrayList<Day>): RecyclerView.Adapter<CalendarAdapter.CalendarHolder>()  {
    inner class CalendarHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_calendar_day,parent,false)
        return CalendarHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        var dayOfWeek = holder.itemView.findViewById<TextView>(R.id.text_DayOfWeek)
        var day = holder.itemView.findViewById<TextView>(R.id.text_Day)

        var dayString: String = ""
        when(days[position].dayOfWeek){
            1 -> dayString = "Sun"
            2 -> dayString = "Mon"
            3 -> dayString = "Tue"
            4 -> dayString = "Wed"
            5 -> dayString = "Thu"
            6 -> dayString = "Fri"
            7 -> dayString = "Sat"
        }

        dayOfWeek.text = dayString
        day.text = days[position].day.toString()

        if (days[position].dayOfWeek == 1)
        {
            dayOfWeek.setTextColor(Color.RED)
            day.setTextColor(Color.RED)
        }
        else
        {
            dayOfWeek.setTextColor(Color.BLACK)
            day.setTextColor(Color.BLACK)
        }

        if(days[position].isEnabled)
        {
            dayOfWeek.setBackgroundColor(Color.GRAY)
            day.setBackgroundColor(Color.GRAY)
        }
        else
        {
            dayOfWeek.setBackgroundColor(Color.WHITE)
            day.setBackgroundColor(Color.WHITE)
        }

        holder.itemView.setOnClickListener {
            DataSource.actual_day = days[position].day.toString() + "-" + days[position].month.toString()+"-"+days[position].year.toString()

            for(dayItem in days){
                if(dayItem.day == DataSource.selectedDay && dayItem.month == DataSource.selectedMonth && dayItem.year == DataSource.selectedYear && dayItem.isEnabled == true)
                {
                    dayItem.isEnabled = false
                    dayOfWeek.setBackgroundColor(Color.WHITE)
                    day.setBackgroundColor(Color.WHITE)
                    this.notifyDataSetChanged()
                }
            }

            days[position].isEnabled = true
            DataSource.selectedDay = days[position].day
            DataSource.selectedMonth = days[position].month
            DataSource.selectedYear = days[position].year
            dayOfWeek.setBackgroundColor(Color.GRAY)
            day.setBackgroundColor(Color.GRAY)
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }
}