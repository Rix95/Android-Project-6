package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddItemActivity : AppCompatActivity() {
    private lateinit var waterDao: WaterDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        waterDao = AppDatabase.getInstance(this).waterDao()
        val saveButton: Button = findViewById(R.id.saveButton)
        val editDate: EditText = findViewById(R.id.dateEntry)
        val editQuantity: EditText = findViewById(R.id.quantityEntry)
        val editUnit: EditText = findViewById(R.id.unitEntry)

        saveButton.setOnClickListener {
            val entryDate = editDate.text.toString()
            val entryQuantity = editQuantity.text.toString().toLong()
            val entryUnit = editUnit.text.toString()


            val waterEntity = WaterEntity(date = entryDate, quantity = entryQuantity, unit = entryUnit )

            // Insert the data into the database using the DAO
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    waterDao.insert(waterEntity)
                    fun printDatabaseContents() {
                        val waterEntries = waterDao.getAllWaterEntries()
                        for (entry in waterEntries) {
                            Log.d("DatabaseEntry", "ID: ${entry.id}, Date: ${entry.date}, Quantity: ${entry.quantity}, Unit: ${entry.unit}")
                        }
                    }
                    printDatabaseContents()


                }
            }

            // Finish the activity after saving the data
            finish()
        }


    }
}