package com.example.gitmessengerbotdemo3.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gitmessengerbotdemo3.*
import com.example.gitmessengerbotdemo3.common.util.GlideImage
import com.example.gitmessengerbotdemo3.common.util.SystemUiController
import com.example.gitmessengerbotdemo3.common.util.doDelay
import com.example.gitmessengerbotdemo3.main.view.MainActivity

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiController(window).setSystemBarsColor(color = Color(0xFF757575))
        setContent { MaterialTheme { Content() } }
        doDelay(2000) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    @Composable
    private fun Content() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.primary)
        ) {
            GlideImage(src = R.drawable.icon_splash, modifier = Modifier.fillMaxSize())
        }
    }
}
