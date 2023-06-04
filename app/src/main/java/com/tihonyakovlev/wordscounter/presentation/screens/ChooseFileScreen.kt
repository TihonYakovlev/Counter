package com.tihonyakovlev.wordscounter.presentation.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tihonyakovlev.wordscounter.presentation.viewmodels.FileUploadViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ChooseFileScreen(viewModel: FileUploadViewModel = getViewModel()) {

    val characterCount by viewModel.characterCount.collectAsState()

    val pickFileLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            viewModel.processFile(uri!!)
        }


    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                pickFileLauncher.launch("text/plain")
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Добавить файл")
        }
        Text("")
        Text(
            text = "Количество символов: $characterCount",
            modifier = Modifier.padding(16.dp)
        )
    }
}

