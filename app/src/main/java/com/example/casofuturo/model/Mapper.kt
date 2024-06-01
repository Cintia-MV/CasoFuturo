package com.example.casofuturo.model

import com.example.casofuturo.model.local.entities.CoursesDetailEntity
import com.example.casofuturo.model.local.entities.CoursesEntity
import com.example.casofuturo.model.remote.fromInternet.CourseDetail
import com.example.casofuturo.model.remote.fromInternet.Courses


fun fromInternetCoursesEntity(coursesList: List<Courses>): List<CoursesEntity> {

    return coursesList.map {

        CoursesEntity(
            id = it.id,
            title = it.title,
            previewDescription = it.previewDescription,
            image = it.image,
            weeks = it.weeks,
            start = it.start

        )
    }

}


fun fromInternetCourseDatailEntity(course: CourseDetail): CoursesDetailEntity{
    return CoursesDetailEntity(
        id = course.id,
        title = course.title,
        previewDescription = course.previewDescription,
        image = course.image,
        weeks = course.weeks,
        start = course.start,
        tuition = course.tuition,
        minimumSkill = course.minimumSkill,
        scholarshipsAvailabl = true,
        modality = course.modality

    )
}