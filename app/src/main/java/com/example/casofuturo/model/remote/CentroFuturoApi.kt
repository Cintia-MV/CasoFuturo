package com.example.casofuturo.model.remote


import com.example.casofuturo.model.remote.fromInternet.CourseDetail
import com.example.casofuturo.model.remote.fromInternet.Courses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CentroFuturoApi {

    @GET("courses")
    suspend fun fetchListCoursesList(): Response<List<Courses>>

    @GET("courses/{id}")
    suspend fun fetchDetailCourse(@Path("id") id:String): Response<CourseDetail>
}