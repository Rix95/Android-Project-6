package com.codepath.articlesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val bestSellerBooksFragment: Fragment = WaterEntryFragment()
        val articleListFragment: Fragment = StatsFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_books -> fragment = bestSellerBooksFragment
                R.id.nav_articles -> fragment = articleListFragment
            }
            fragmentManager.beginTransaction().replace(R.id.article_frame_layout, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_books
    }
}