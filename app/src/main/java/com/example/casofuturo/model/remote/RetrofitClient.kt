package com.example.casofuturo.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private const val BASE_URL = "https://caso-invest-center-mariocanedo.vercel.app/"


        fun getRetrofit(): CentroFuturoApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CentroFuturoApi::class.java)
        }
    }
}