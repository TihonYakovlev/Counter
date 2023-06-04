package com.tihonyakovlev.wordscounter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.tihonyakovlev.wordscounter.presentation.screens.ChooseFileScreen
import com.tihonyakovlev.wordscounter.presentation.screens.InsertTextScreen
import com.tihonyakovlev.wordscounter.presentation.screens.SettingsScreen
import com.tihonyakovlev.wordscounter.presentation.viewmodels.FileUploadViewModel
import com.tihonyakovlev.wordscounter.ui.theme.WordsCounterTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordsCounterTheme {
                MyBottomNavigation()
            }
        }
    }
}

@Composable
fun MyBottomNavigation() {
    val navItems = listOf(
        NavItem("Выбрать файл", Icons.Filled.Person),
        NavItem("Вставить текст", Icons.Filled.Menu),
        NavItem("Настройки", Icons.Filled.Settings)
    )
    var selectedIndex by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            BottomNavigation {
                navItems.forEachIndexed { index, navItem ->
                    BottomNavigationItem(
                        icon = { Icon(navItem.icon, contentDescription = navItem.title) },
                        label = { Text(navItem.title) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedIndex) {
            0 -> ChooseFileScreen()
            1 -> InsertTextScreen()
            2 -> SettingsScreen()
        }
    }
}
data class NavItem(val title: String, val icon: ImageVector)


