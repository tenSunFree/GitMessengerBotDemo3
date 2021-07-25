package com.example.gitmessengerbotdemo3.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.foundation.lazy.items
import com.example.gitmessengerbotdemo3.common.util.GlideImage
import com.example.gitmessengerbotdemo3.R
import com.example.gitmessengerbotdemo3.common.remote.BaseResponse
import com.example.gitmessengerbotdemo3.common.util.ShowToast
import com.example.gitmessengerbotdemo3.main.model.CommentsBean
import com.example.gitmessengerbotdemo3.main.viewModel.MainViewModel

@Composable
fun MeetingContent(viewModel: MainViewModel) {
    val alreadyInitState = remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF161b1E))
    ) {
        val (topBar, content) = createRefs()
        GlideImage(
            src = R.drawable.icon_meeting_top_bar,
            modifier = Modifier.constrainAs(topBar) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )
        val contentModifier = Modifier.constrainAs(content) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(topBar.bottom)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        }
        InitContent(viewModel, contentModifier)
        InitState(alreadyInitState, viewModel)
    }
}

@Composable
private fun Loading() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        val (indicator) = createRefs()
        CircularProgressIndicator(
            modifier = Modifier
                .background(Color.Transparent)
                .constrainAs(indicator) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            color = Color.White
        )
    }
}

@Composable
private fun InitContent(
    viewModel: MainViewModel,
    modifier: Modifier
) {
    viewModel.comments.apply {
        when (value) {
            is BaseResponse.Loading -> {
                Loading()
            }
            is BaseResponse.Success<*> -> {
                val list: List<CommentsBean.Item> =
                    (value as BaseResponse.Success<CommentsBean>).response.toList()
                LazyColumn(modifier = modifier) {
                    items(list) { item -> SuccessLazyColumnItem(item) }
                }
            }
            is BaseResponse.Error -> {
                ShowToast((value as BaseResponse.Error).exception.message!!)
            }
        }
    }
}

@Composable
private fun SuccessLazyColumnItem(message: CommentsBean.Item) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF161b1E))
    ) {
        val (name, email, button, divider) = createRefs()
        Text(
            text = message.name,
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            modifier = Modifier
                .padding(
                    top = 8.dp, bottom = 0.dp,
                    start = 16.dp, end = 16.dp
                )
                .constrainAs(name) {
                    start.linkTo(parent.start)
                    end.linkTo(button.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(email.top)
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = message.email,
            style = TextStyle(fontSize = 16.sp, color = Color.White),
            maxLines = 1,
            modifier = Modifier
                .padding(
                    top = 8.dp, bottom = 8.dp,
                    start = 16.dp, end = 16.dp
                )
                .constrainAs(email) {
                    start.linkTo(parent.start)
                    end.linkTo(button.start)
                    top.linkTo(name.bottom)
                    bottom.linkTo(divider.top)
                    width = Dimension.fillToConstraints
                }
        )
        OutlinedButton(
            onClick = { },
            shape = RoundedCornerShape(48),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Red,
                backgroundColor = Color(0xFF44A943)
            ),
            modifier = Modifier
                .padding(
                    top = 0.dp, bottom = 0.dp,
                    start = 0.dp, end = 16.dp
                )
                .constrainAs(button) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text(text = "開始", style = TextStyle(color = Color.White))
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .padding(start = 16.dp)
            .background(Color.Gray)
            .constrainAs(divider) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
private fun InitState(state: MutableState<Boolean>, viewModel: MainViewModel) {
    if (state.value) return
    viewModel.getComments()
    state.value = true
}