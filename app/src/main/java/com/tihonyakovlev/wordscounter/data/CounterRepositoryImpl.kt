package com.tihonyakovlev.wordscounter.data

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.tihonyakovlev.wordscounter.domain.CounterRepository
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class CounterRepositoryImpl(private val context: Context) : CounterRepository {
    override suspend fun processFile(uri: Uri): Int {

        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        var count = 0
        var characterCount = 0
        while (reader.readLine().also { line = it } != null) {
            count += line?.length ?: 0
            Log.d("CounterRepositoryImpl", "Count: $count")
        }
        characterCount = count
        Log.d("CounterRepositoryImpl", "characterCount: $characterCount")
        return characterCount
    }
}
