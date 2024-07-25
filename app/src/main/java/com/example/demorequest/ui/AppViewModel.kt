package com.example.demorequest.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demorequest.network.CfApi
import com.example.demorequest.network.UserData
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface FetchVerdict {
    data class Success(val result: List<UserData>): FetchVerdict
    data class Error(val errorMsg: String): FetchVerdict
    data object Loading: FetchVerdict
}

class AppViewModel : ViewModel() {
    var fetchState: FetchVerdict by mutableStateOf(FetchVerdict.Loading)
        private set

    fun loadData(handle: String) {
        viewModelScope.launch {
            try {
                val res = CfApi.retrofitService.getInfo(handle)
                fetchState = if(res.status == "OK") {
                    FetchVerdict.Success(res.result)
                } else {
                    FetchVerdict.Error(res.comment ?: "Unknown API error")
                }
            } catch(e: IOException) {
                fetchState = FetchVerdict.Error("Network Error")
            } catch (e: Exception) {
                fetchState = FetchVerdict.Error(e.message ?: "Unknown")
            }
        }
    }
}