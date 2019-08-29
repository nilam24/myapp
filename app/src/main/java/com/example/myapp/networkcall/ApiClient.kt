package com.example.myapp.networkcall

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient  {

    companion object{

        lateinit var apiInterface: ApiInterface
        var BASE_URL = "https://api.foursquare.com/v2/venues/search/"
        var logging = HttpLoggingInterceptor()

        fun getApiClient(): ApiInterface{

            logging.level = HttpLoggingInterceptor.Level.BODY
            var httpclient = OkHttpClient.Builder()
            apiInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient.build())
                .build().create(ApiInterface::class.java)
            return apiInterface

        }
    }
}