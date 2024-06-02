package com.example.casofuturo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.casofuturo.model.CentroFuturoRepository
import com.example.casofuturo.model.local.database.CourseDataBase
import com.example.casofuturo.model.local.entities.CoursesDetailEntity
import com.example.casofuturo.model.local.entities.CoursesEntity
import kotlinx.coroutines.launch

class CoursesViewModel(application: Application): AndroidViewModel(application) {


    //Conexion repositorio
    private val repository : CentroFuturoRepository

    //Entidad
    private val courseDetailLiveData = MutableLiveData<CoursesDetailEntity>()

    //Para seleccionar
    private var isSelected : String ="-1"

    init {
        val bd = CourseDataBase.getDataBase(application)
        val centroFuturoDao = bd.getCentroFuturoDao()
        repository = CentroFuturoRepository(centroFuturoDao)

        //Llamar a fetchCourse
        viewModelScope.launch {
            repository.fetchCourse()
        }
    }

    //Listado de los elementos
    fun getCoursesList(): LiveData<List<CoursesEntity>> = repository.coursesListLiveDataRepo


    //Detalle de los cursos
    fun getCourseDetail(): LiveData<CoursesDetailEntity> = courseDetailLiveData

    fun getCourseDetailByIdFromInternet(id:String) = viewModelScope.launch {
        val courseDetail = repository.fetchDetailCourse(id)
        courseDetail?.let{
            courseDetailLiveData.postValue(it)
        }
    }
}