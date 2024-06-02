package com.example.casofuturo

import com.example.casofuturo.model.local.entities.CoursesEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TestCoursesEntity {

    private lateinit var coursesEntity : CoursesEntity

    @Before
    fun setUpt(){
        coursesEntity = CoursesEntity(
            id = "1",
            title = "Prueba android",
            previewDescription = "Prueba test",
            image = "imagen",
            weeks = 2,
            start = "mayo 2024"
        )
    }

    @After
    fun tearDown(){

    }

    @Test
    fun testCourseConstructor(){
        //Verificar valores asignados
        assert(coursesEntity.id == "1")
        assert(coursesEntity.title == "Prueba android")
        assert(coursesEntity.previewDescription == "Prueba test")
        assert(coursesEntity.image == "imagen")
        assert(coursesEntity.weeks == 2)
        assert(coursesEntity.start == "mayo 2024")
    }
}