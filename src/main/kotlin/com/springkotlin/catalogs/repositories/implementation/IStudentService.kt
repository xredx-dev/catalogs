package com.springkotlin.catalogs.repositories.implementation

import com.springkotlin.catalogs.dtos.StudentDTO


interface IStudentService {
    fun add(studentDTO: StudentDTO): StudentDTO
    fun update(id: Int, studentDTO: StudentDTO): StudentDTO
    fun getAll(): List<StudentDTO>
    fun delete(id: Int): Unit
}