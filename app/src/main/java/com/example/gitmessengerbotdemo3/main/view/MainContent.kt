package com.example.gitmessengerbotdemo3.main.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gitmessengerbotdemo3.R
import com.example.gitmessengerbotdemo3.main.model.BottomBarBean
import com.example.gitmessengerbotdemo3.main.model.MainTab
import com.example.gitmessengerbotdemo3.main.viewModel.MainViewModel

private var tabIndex by mutableStateOf(MainTab.Home)

@Composable
fun MainContent(viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Crossfade(
                targetState = tabIndex,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 60.dp)
            ) { index ->
                when (index) {
                    MainTab.Home -> HomeContent()
                    MainTab.Meeting -> MeetingContent(viewModel)
                    MainTab.Record -> RecordContent()
                }
            }
            BottomBar()
        }
    }
}

@Composable
fun BottomBar() {
    val items = listOf(
        BottomBarBean(title = "首頁", icon = R.drawable.ic_home_24, id = MainTab.Home),
        BottomBarBean(title = "會議", icon = R.drawable.ic_meeting_24, id = MainTab.Meeting),
        BottomBarBean(title = "錄製檔", icon = R.drawable.ic_record_24, id = MainTab.Record),
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val modifier = Modifier
                    .weight(1f)
                    .clickable { tabIndex = item.id }
                val fancyItemColor by animateColorAsState(
                    if (tabIndex == item.id) {
                        Color(0xFF00A0D1)
                    } else {
                        Color.White
                    }
                )
                BottomBarItem(modifier, item, fancyItemColor)
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    modifier: Modifier,
    item: BottomBarBean,
    fancyItemColor: Color
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (item.icon != null) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    tint = fancyItemColor
                )
            }
            if (item.title.isNotBlank()) {
                Text(
                    text = item.title,
                    color = fancyItemColor,
                    modifier = Modifier.padding(top = 4.dp),
                    fontSize = 11.sp,
                )
            }
        }
    }
}
