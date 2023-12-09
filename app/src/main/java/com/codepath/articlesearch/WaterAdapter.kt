package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

const val WATER_ADD = "WATER_ADD"
private const val TAG = "WaterAdapter"

class WaterAdapter(private val context: Context, private val entries: List<DisplayWater>) :
    RecyclerView.Adapter<WaterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry)
    }

    override fun getItemCount() = entries.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val dateTextView = itemView.findViewById<TextView>(R.id.date)
        private val quantityTextView = itemView.findViewById<TextView>(R.id.quantity)
        private val unitTextView = itemView.findViewById<TextView>(R.id.unit)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(entry: DisplayWater) {
            dateTextView.text = entry.date
            quantityTextView.text = entry.quantity.toString()
            unitTextView.text = entry.unit


        }

        override fun onClick(v: View?) {
            // Get selected article
//            val entry = entries[absoluteAdapterPosition]
//
//            // Navigate to Details screen and pass selected article
//            val intent = Intent(context, AddItemActivity::class.java)
//            intent.putExtra(WATER_ADD, entry)
//            context.startActivity(intent)

        }
    }
}