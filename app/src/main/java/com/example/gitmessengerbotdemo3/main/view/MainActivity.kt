package com.example.gitmessengerbotdemo3.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import com.example.gitmessengerbotdemo3.common.util.SystemUiController
import com.example.gitmessengerbotdemo3.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).setSystemBarsColor(color = Color(0xFF000000))
        setContent { MaterialTheme { MainContent(viewModel) } }
    }
}