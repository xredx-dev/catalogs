package com.springkotlin.catalogs.repositories

import com.springkotlin.catalogs.entities.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Int>