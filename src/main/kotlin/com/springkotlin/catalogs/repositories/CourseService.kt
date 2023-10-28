package com.springkotlin.catalogs.repositories

import com.springkotlin.catalogs.dtos.CourseDTO
import com.springkotlin.catalogs.entities.Course
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseRepositoryService {
    companion object: KLogging()

    fun add(courseDTO: CourseDTO): CourseDTO {
        val courseEntity = courseDTO.let { Course(it.id, it.name, it.category) }

        return courseEntity.let {
            CourseDTO(it.id, it.name, it.category)
        }
    }
}
