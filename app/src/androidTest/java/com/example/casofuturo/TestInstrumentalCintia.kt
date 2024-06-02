package com.example.casofuturo

import androidx.lifecycle.Observer
import com.example.casofuturo.model.local.CentroFuturoDao
import com.example.casofuturo.model.local.database.CourseDataBase

import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import com.example.casofuturo.model.local.entities.CoursesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CentroFuturoDaoTest {

    // Variables de prueba
    private lateinit var db: CourseDataBase
    private lateinit var centroFuturoDao: CentroFuturoDao

    @Before
    fun setup() {
        // Inicialización de la base de datos y el DAO
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, CourseDataBase::class.java).build()
        centroFuturoDao = db.getCentroFuturoDao()
    }

    @After
    fun teardown() {
        // Cierre de la base de datos
        db.close()
    }

    @Test
    fun testCrudOperations() = runBlocking {
        // Creación de datos de prueba
        val coursesEntity = listOf(
            CoursesEntity("43", "prueba1", "test1", "img1", 4, "junio"),
            CoursesEntity("44", "prueba2", "test2", "img2", 4, "julio")
        )

        // Insertar los datos de prueba en la base de datos
        centroFuturoDao.insertAllCourses(coursesEntity)

        // Observar LiveData en el hilo principal
        runOnUiThread {
            // Obtener LiveData de todos los cursos
            val allCoursesLiveData = centroFuturoDao.getAllCourses()

            // Crear el observador para los cursos
            val coursesObserver = Observer<List<CoursesEntity>> { coursesList ->

                ViewMatchers.assertThat(coursesList, CoreMatchers.not(emptyList()))
                // Realizar las aserciones dentro del observador
                assertEquals(2, coursesList.size)
            }

            // Observar los cursos
            allCoursesLiveData.observeForever(coursesObserver)

            // Quitar el observador después de realizar las aserciones
            allCoursesLiveData.removeObserver(coursesObserver)
        }
    }
}