package com.springkotlin.catalogs.repositories


import com.springkotlin.catalogs.entities.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Int>