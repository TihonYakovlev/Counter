package com.tihonyakovlev.wordscounter.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tihonyakovlev.wordscounter.domain.CounterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FileUploadViewModel(private val counterRepository: CounterRepository) : ViewModel() {
    private val _characterCount = MutableStateFlow(0)
    val characterCount: StateFlow<Int> get() = _characterCount

    fun processFile(uri: Uri) {
        viewModelScope.launch {
            val count = counterRepository.processFile(uri)
            _characterCount.value = count
        }
    }
}
