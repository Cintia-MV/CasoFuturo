package com.example.casofuturo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.casofuturo.databinding.CoursesListBinding
import com.example.casofuturo.model.local.entities.CoursesEntity

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseVH>(){

    //Referencias para el adapter
    private var listCourses = listOf<CoursesEntity>()
    private val selectedCourse = MutableLiveData<CoursesEntity>()

    //Función para actualizar el adapter
    fun update(list: List<CoursesEntity>){
        listCourses = list
        notifyDataSetChanged()
    }

    //Función para seleccionar un curso
    fun selectedCourse(): LiveData<CoursesEntity> = selectedCourse
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVH {
        return CourseVH(CoursesListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listCourses.size

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        val course = listCourses[position]
        holder.bin(course)
    }





    inner class CourseVH(private val mBinding: CoursesListBinding):
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{

        fun bin(course: CoursesEntity){
            Glide.with(mBinding.imgCurso).load(course.image).centerCrop().into(mBinding.imgCurso)
            mBinding.tittleCourse.text = course.title
            mBinding.previewDesc.text = course.previewDescription
            mBinding.weeks.text = "Duración: " + course.weeks.toString() + "semanas"
            mBinding.start.text = course.start

            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedCourse.value = listCourses[adapterPosition]
            Log.d("OnClick", adapterPosition.toString())
        }

    }


}