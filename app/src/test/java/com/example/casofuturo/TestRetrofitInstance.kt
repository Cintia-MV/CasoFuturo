package com.example.casofuturo

import com.example.casofuturo.model.remote.RetrofitClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class TestRetrofitInstance {

    private lateinit var mockWebServer : MockWebServer

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testRetrofit(){
        val expecteBaseUrl = mockWebServer.url("/").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(expecteBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        RetrofitClient.retrofitInstance = retrofit

        val retrofitInstance = RetrofitClient.retrofitInstance

        assertEquals(expecteBaseUrl, retrofitInstance.baseUrl().toString())

    }
}