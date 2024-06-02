package com.example.casofuturo

import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.casofuturo.model.local.CentroFuturoDao
import com.example.casofuturo.model.local.database.CourseDataBase
import com.example.casofuturo.model.local.entities.CoursesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestInstrumentalDao {
    private lateinit var centroFuturoDao: CentroFuturoDao
    private lateinit var db: CourseDataBase


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, CourseDataBase::class.java).build()
        centroFuturoDao = db.getCentroFuturoDao()
    }

    @After
    fun shutDown(){
        db.close()
    }

    @Test
    fun insertCoursesList() = runBlocking {



        val coursesEntity = listOf(

            CoursesEntity("43", "prueba1", "test1", "img1", 4, "junio"),
            CoursesEntity("44", "prueba2", "test2", "img2", 4, "julio")
        )

        centroFuturoDao.insertAllCourses(coursesEntity)

        val coursesLiveData = centroFuturoDao.getAllCourses()

        val coursesList : List<CoursesEntity> = coursesLiveData.value?: emptyList()

        //Verifico el listado
        assertThat(coursesList, not(emptyList()))
        assertThat(coursesList.size, equalTo(2))
    }

}