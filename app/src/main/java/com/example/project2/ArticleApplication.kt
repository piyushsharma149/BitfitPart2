package com.example.project2

import android.app.Application

class ArticleApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}