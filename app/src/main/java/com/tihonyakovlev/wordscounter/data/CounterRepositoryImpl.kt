package com.tihonyakovlev.wordscounter.data

import android.content.Context
import android.net.Uri
import android.util.Log
import com.tihonyakovlev.wordscounter.domain.CounterRepository
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class CounterRepositoryImpl(private val context: Context) : CounterRepository {
    override suspend fun countCharacters(uri: Uri): Int {

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

    override suspend fun countWords(uri: Uri): Int {
        val inputStream = context.contentResolver.openInputStream(uri)
        return try {
            val reader = BufferedReader(InputStreamReader(inputStream))
            var wordCount = 0
            var line: String? = reader.readLine()
            while (line != null) {
                val words = line.trim().split("\\s+".toRegex()) // Разделение строки на слова
                wordCount += words.size
                line = reader.readLine()
            }
            wordCount
        } catch (e: IOException) {
            0
        } finally {
            inputStream?.close()
        }
    }

    override suspend fun countForeignWords(uri: Uri): Int {
        val inputStream = context.contentResolver.openInputStream(uri)
        return try {
            val reader = BufferedReader(InputStreamReader(inputStream))
            var foreignWordCount = 0
            var line: String? = reader.readLine()
            while (line != null) {
                val words = line.trim().split("\\s+".toRegex()) // Разделение строки на слова
                for (word in words) {
                    if (isForeignWord(word)) { // Проверка, является ли слово иностранным
                        foreignWordCount++
                    }
                }
                line = reader.readLine()
            }
            foreignWordCount
        } catch (e: IOException) {
            0
        } finally {
            inputStream?.close()
        }
    }
    private fun isForeignWord(word: String): Boolean {
        val russianAlphabet = ('а'..'я') + ('А'..'Я') // Русский алфавит
        for (char in word) {
            if (char !in russianAlphabet && char.isLetter()) {
                return true // Если найден символ, не принадлежащий русскому алфавиту, считаем слово иностранным
            }
        }
        return false // Если все символы принадлежат русскому алфавиту, считаем слово неиностранным
    }
}
