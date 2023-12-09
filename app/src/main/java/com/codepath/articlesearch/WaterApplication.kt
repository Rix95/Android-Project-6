package com.codepath.articlesearch

import android.app.Application

class WaterApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }

}