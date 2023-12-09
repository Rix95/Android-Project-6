package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StatsFragment : Fragment() {
    private lateinit var waterDao: WaterDao
    private lateinit var text1: TextView
    private lateinit var text2: TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stats, container, false)
       waterDao = AppDatabase.getInstance(requireContext()).waterDao()

        text1 = view.findViewById(R.id.total_entries)
        text2 = view.findViewById(R.id.average)


        lifecycleScope.launch {
            // Execute database operations on the background thread
            withContext(Dispatchers.IO) {
                val numberOfEntries = waterDao.getNumberOfEntries()
                val average = waterDao.getAverageLongAttribute()

                Log.d("Hello", numberOfEntries.toString())
                Log.d("Hello", average.toString())

                // Update UI elements on the main thread
                withContext(Dispatchers.Main) {
                    text1.text = numberOfEntries.toString()
                    text2.text = average.toString()
                }
            }
        }

        return view
    }




    companion object {
        fun newInstance(): WaterEntryFragment {
            return WaterEntryFragment()
        }
    }



}

