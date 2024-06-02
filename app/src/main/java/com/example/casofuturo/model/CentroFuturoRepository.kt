package com.example.casofuturo.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.casofuturo.model.local.CentroFuturoDao
import com.example.casofuturo.model.local.entities.CoursesDetailEntity
import com.example.casofuturo.model.local.entities.CoursesEntity
import com.example.casofuturo.model.remote.RetrofitClient
import kotlin.jvm.internal.Intrinsics.Kotlin

class CentroFuturoRepository (private val centroFuturoDao : CentroFuturoDao){

    //Retrofit client
    private val networkService = RetrofitClient.getRetrofit()

    //Lista de cursos del DAO
    val coursesListLiveDataRepo = centroFuturoDao.getAllCourses()

    //Detalle de un curso
    val courseDetailEntity = MutableLiveData<CoursesDetailEntity>()

    //Listado
    val coursesEntity = MutableLiveData<CoursesEntity>()

    suspend fun fetchCourse(){
        val service = kotlin.runCatching { networkService.fetchListCoursesList()}

        service.onSuccess {
            when(it.code()){
                in 200 ..299 -> it.body()?.let{
                    Log.d("Cursos", it.toString())
                    centroFuturoDao.insertAllCourses(fromInternetCoursesEntity(it))
                }
                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
        }
            service.onFailure {
                Log.d("ERROR", "${it.message}")
            }

        }
    }


    suspend fun fetchDetailCourse(id: String): CoursesDetailEntity?{
        val service = kotlin.runCatching { networkService.fetchDetailCourse(id)}

        return service.getOrNull()?.body()?.let{courseDetail ->
            val coursesDetailEntity = fromInternetCourseDatailEntity(courseDetail)
            centroFuturoDao.insertDetailCourse(coursesDetailEntity)
            coursesDetailEntity

        }
    }
}