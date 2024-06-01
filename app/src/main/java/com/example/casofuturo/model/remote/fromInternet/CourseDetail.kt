package com.example.casofuturo.model.remote.fromInternet

data class CourseDetail (

    val id : String,
    val title : String,
    val previewDescription : String,
    val image : String,
    val weeks : Int,
    val start : String,
    val tuition : String,
    val minimumSkill : String,
    val scholarshipsAvailabl : Boolean,
    val modality : String
)