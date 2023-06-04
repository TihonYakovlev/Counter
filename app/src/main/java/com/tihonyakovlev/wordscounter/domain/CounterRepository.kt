package com.tihonyakovlev.wordscounter.domain

import android.net.Uri

interface CounterRepository {
    suspend fun countCharacters(uri: Uri): Int
    suspend fun countWords(uri: Uri): Int
    suspend fun countForeignWords(uri: Uri): Int
}