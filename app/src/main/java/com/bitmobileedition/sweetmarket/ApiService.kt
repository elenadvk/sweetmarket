package com.bitmobileedition.sweetmarket

import retrofit2.http.GET
//import retrofit2.http.Path



interface ApiService {
    @GET("/items")
    suspend fun getItems(): List<Item>
}