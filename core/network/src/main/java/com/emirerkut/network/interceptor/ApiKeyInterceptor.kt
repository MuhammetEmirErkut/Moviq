package com.emirerkut.network.interceptor

import com.emirerkut.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .removeHeader("api_key")
            .url(request.url)
            .build()

        return chain.proceed(url)
    }
}