package com.springkotlin.catalogs.repositories.implementation

import com.springkotlin.catalogs.dtos.CourseDTO


interface ICourseService {
    fun add(courseDTO: CourseDTO): CourseDTO
    fun update(id: Int, courseDTO: CourseDTO): CourseDTO
    fun getAll(): List<CourseDTO>
    fun delete(id: Int): Unit
}