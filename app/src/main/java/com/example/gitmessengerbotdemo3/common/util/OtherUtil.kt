package com.example.gitmessengerbotdemo3.common.util

import android.os.Handler
import android.os.Looper

fun doDelay(ms: Long, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action() }, ms)
}

