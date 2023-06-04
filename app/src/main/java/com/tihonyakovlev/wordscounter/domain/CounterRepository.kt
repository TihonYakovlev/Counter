package com.tihonyakovlev.wordscounter.domain

import android.net.Uri

interface CounterRepository {
    suspend fun processFile(uri: Uri): Int
}