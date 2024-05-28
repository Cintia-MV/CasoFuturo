package com.example.casofuturo.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.casofuturo.model.local.entities.CoursesDetailEntity
import com.example.casofuturo.model.local.entities.CoursesEntity
import com.example.casofuturo.model.remote.fromInternet.Courses

@Dao
interface CentroFuturoDao {

    //CURSOS
    //Insertar lista de cursos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCourses(courseList: List<CoursesEntity>)

    //Insertar un curso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CoursesEntity)

    //Actualizar
    @Update
    suspend fun updateCourse(courses: CoursesEntity)

    //Delete
    @Delete
    suspend fun deleteCourse(courses: CoursesEntity)

    @Query("SELECT * FROM course_list_table")
    fun getAllCourses(): LiveData<List<CoursesEntity>>

    @Query("SELECT * FROM course_list_table WHERE id = :id")
    fun getCourseId(id: String): LiveData<CoursesEntity>



    //DETALLE CURSOS
    //Insertar lista de detalles
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetailsCourse(courseDetail : List<CoursesDetailEntity>)

    //Insertar un detalle
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailCourse(courseDetail : CoursesDetailEntity)

    //Actualizar detalle
    @Update
    suspend fun updateDetailCourse(courseDetail: CoursesDetailEntity)

    //Eliminar detalle
    @Delete
    suspend fun deleteDetailCourse(courseDetail: CoursesDetailEntity)

    //Mostrar todos los detalles
    @Query("SELECT * FROM course_detail_table")
    fun getAllDetailCourses():LiveData<List<CoursesDetailEntity>>

    //Mostrar detalle por id
    @Query("SELECT * FROM course_detail_table WHERE id = :id")
    fun getDetailCourseId(id: String):LiveData<CoursesDetailEntity>
}