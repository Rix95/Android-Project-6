package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.R.layout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WaterEntryFragment : Fragment() {
//    private lateinit var waterRecyclerView: RecyclerView
//    private lateinit var binding: ActivityMainBinding
    private val entriesWater = mutableListOf<DisplayWater>()
    private lateinit var waterDao: WaterDao
    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.fragment_best_seller_books_list, container, false)
        val recyclerView = view.findViewById<View>(R.id.entries) as RecyclerView
        val context = view.context
        waterDao = AppDatabase.getInstance(requireContext()).waterDao()
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        val adapter = WaterAdapter(requireContext(), entriesWater)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            (requireActivity().application as WaterApplication).db.waterDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayWater(
                        entity.date,
                        entity.quantity,
                        entity.unit,
                    )
                }.also { mappedList ->
                    entriesWater.clear()
                    entriesWater.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        val addButton: Button = view.findViewById(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(requireContext(), AddItemActivity::class.java)
            startActivity(intent)
            adapter.notifyDataSetChanged()
        }

        val deleteButton: Button = view.findViewById(R.id.deleteButton)
        deleteButton.setOnClickListener {
            // Use a coroutine to delete all entries asynchronously
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    waterDao.deleteAll()
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
