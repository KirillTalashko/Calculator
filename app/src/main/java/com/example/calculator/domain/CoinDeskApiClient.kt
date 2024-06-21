package com.example.calculator.domain

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CoinDeskApiClient {

    private val client = OkHttpClient()

    fun fetchCurrentPrice(callback: (String?, Exception?) -> Unit) {
        val request = Request.Builder()
            .url("https://api.coindesk.com/v1/bpi/currentprice.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Обработка ошибки
                callback(null, e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        callback(null, IOException("Unexpected code $response"))
                        return
                    }
                    val responseData = response.body?.string()
                    callback(responseData, null)
                }
            }
        })
    }
}