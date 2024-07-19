package com.example.calculator.domain

import com.example.calculator.extentions.log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class CoinDeskApiClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor (MyInterceptor("key"))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val request = Request.Builder()
        .url("https://api.coindesk.com/v1/bpi/currentprice.json")
        .build()

    fun fetchCurrentPrice(callback: (String?, Exception?) -> Unit) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null, e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        callback(null, Exception("Unexpected code $response"))
                        throw IOException(
                            "Запрос к серверу не был успешен:" +
                                    " ${response.code} ${response.message}"
                        )
                    }
                    callback(response.body.toString(), null)
                }
            }
        })
    }

    class MyInterceptor(private val key: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("apiKey", key)
                .build()
            return chain.proceed(request)
        }
    }
}