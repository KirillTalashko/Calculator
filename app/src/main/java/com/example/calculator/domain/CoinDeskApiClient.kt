package com.example.calculator.domain

import com.example.calculator.domain.Request.request
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit


class CoinDeskApiClient {
    fun fetchCurrentPrice(callback: (String?, Exception?) -> Unit) {
        MyOkhttp.okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null, e)
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        callback(null, IOException("Unexpected code $response"))
                        return
                    }
                    callback(response.body?.string(), null)
                }
            }
        })
    }
}

object Request {
    val request = Request.Builder()
        .url("https://api.coindesk.com/v1/bpi/currentprice.json")
        .build()
}

object MyOkhttp {
    val okHttpClient =
        OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)//весь вызов
            .connectTimeout(10, TimeUnit.SECONDS) //время подключения к серверу
            .readTimeout(10, TimeUnit.SECONDS) // время которое нужно чтобы считать ответ от сервера
            .writeTimeout(10, TimeUnit.SECONDS) // время отдать данные
            .addInterceptor(MyInterceptor("key"))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
}

class MyInterceptor(private val key: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("apiKey", key)
            .build()
        return chain.proceed(request)
    }

}