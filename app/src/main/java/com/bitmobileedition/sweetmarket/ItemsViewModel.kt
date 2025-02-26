package com.bitmobileedition.sweetmarket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class ItemsViewModel: ViewModel() {
    @OptIn(ExperimentalSerializationApi::class)
    val retrofit = Retrofit.Builder()
        .baseUrl("http://84.246.85.148:8080") //---------------------------------------------------------------------------------
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build().create(ApiService::class.java)
    var originalList: List<Item>? = null
    val items: MutableLiveData<List<Item>> = MutableLiveData()
    init {
        viewModelScope.launch {
            originalList = retrofit.getItems()
            items.postValue(originalList)
        }
    }
    fun filter(query: String? = null){
        if(query != null)
            items.value = originalList?.filter { it.title.contains(query.trim(), ignoreCase = true) || it.desc.contains(query.trim(), ignoreCase = true) }
    }
}