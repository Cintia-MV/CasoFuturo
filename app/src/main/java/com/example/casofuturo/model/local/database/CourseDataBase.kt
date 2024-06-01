package com.example.casofuturo.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.casofuturo.model.local.CentroFuturoDao
import com.example.casofuturo.model.local.entities.CoursesDetailEntity
import com.example.casofuturo.model.local.entities.CoursesEntity

@Database(entities = [CoursesDetailEntity::class, CoursesEntity::class], version = 1, exportSchema = false)
abstract class CourseDataBase : RoomDatabase(){
    abstract fun getCentroFuturoDao(): CentroFuturoDao


    companion object{
        @Volatile
        private var INSTANCE: CourseDataBase? = null

        fun getDataBase(context: Context): CourseDataBase{
            val temInstance = INSTANCE
            if(temInstance!= null){
                return temInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CourseDataBase::class.java,
                    "CURSOFUTURO"
                )
                    .build()
                INSTANCE= instance
                return instance
            }
        }
    }
}