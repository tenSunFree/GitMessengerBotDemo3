package com.example.gitmessengerbotdemo3.main.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitmessengerbotdemo3.common.remote.BaseResponse
import com.example.gitmessengerbotdemo3.main.model.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _comments = mutableStateOf<BaseResponse>(BaseResponse.Loading())
    val comments: MutableState<BaseResponse> get() = _comments

    fun getComments() {
        viewModelScope.launch {
            try {
                _comments.value = BaseResponse.Loading()
                repository.getComments().collect { comments -> _comments.value = comments }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _comments.value = BaseResponse.Error(e)
                }
            }
        }
    }
}