package com.tihonyakovlev.wordscounter.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tihonyakovlev.wordscounter.domain.CounterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FileUploadViewModel(private val counterRepository: CounterRepository) : ViewModel() {
    private val _characterCount = MutableStateFlow(0)
    val characterCount: StateFlow<Int> get() = _characterCount.asStateFlow()

    private val _wordsCount = MutableStateFlow(0)
    val wordsCount: StateFlow<Int> get() = _wordsCount.asStateFlow()

    private val _foreignWordsCount = MutableStateFlow(0)
    val foreignWordsCount: StateFlow<Int> get() = _foreignWordsCount.asStateFlow()

    fun charactersCount(uri: Uri) {
        viewModelScope.launch {
            val ccount = counterRepository.countCharacters(uri)
            _characterCount.update { ccount }
        }
    }

    fun wordsCount(uri: Uri) {
        viewModelScope.launch {
            val wcount = counterRepository.countWords(uri)
            _wordsCount.value = wcount
        }
    }

    fun foreignWordsCount(uri: Uri) {
        viewModelScope.launch {
            val fwcount = counterRepository.countForeignWords(uri)
            _foreignWordsCount.value = fwcount
        }
    }
}
