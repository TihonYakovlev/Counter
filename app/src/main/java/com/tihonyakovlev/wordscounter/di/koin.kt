package com.tihonyakovlev.wordscounter.di

import com.tihonyakovlev.wordscounter.data.CounterRepositoryImpl
import com.tihonyakovlev.wordscounter.domain.CounterRepository
import com.tihonyakovlev.wordscounter.presentation.viewmodels.FileUploadViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf

import org.koin.dsl.module


val appModule = module {
    single<CounterRepository> { CounterRepositoryImpl(androidContext()) }
    viewModel { FileUploadViewModel(get()) }
}