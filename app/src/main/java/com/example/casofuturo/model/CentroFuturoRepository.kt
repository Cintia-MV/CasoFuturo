package com.example.casofuturo.model

import com.example.casofuturo.model.local.CentroFuturoDao
import com.example.casofuturo.model.remote.RetrofitClient

class CentroFuturoRepository (private val centroFuturoDao : CentroFuturoDao){

    //Retrofit client
    private val networkService = RetrofitClient.getRetrofit()

    //Instancia del DAO
    val coursesListLiveData = centroFuturoDao.getAllCourses()
}