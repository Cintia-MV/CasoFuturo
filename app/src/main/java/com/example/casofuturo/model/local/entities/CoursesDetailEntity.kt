package com.example.casofuturo.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_detail_table")
data class CoursesDetailEntity (

    @PrimaryKey
    val id : String,
    val title : String,
    val previewDescription : String,
    val image : String,
    val weeks : Int,
    val start : String,
    val tuition : String,
    val miniumSkill : String,
    val ScholarShioAvailable : Boolean,
    val modality : String

)