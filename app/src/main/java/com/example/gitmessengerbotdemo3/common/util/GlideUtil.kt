package com.example.gitmessengerbotdemo3.common.util

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun GlideImage(modifier: Modifier, src: Any) {
    val context = LocalContext.current
    val imageView = ImageView(context).apply { scaleType = ImageView.ScaleType.FIT_XY }
    AndroidView(
        factory = { imageView },
        modifier = modifier
    ) {
        GlideApp.with(context).load(src).into(it)
    }
}
