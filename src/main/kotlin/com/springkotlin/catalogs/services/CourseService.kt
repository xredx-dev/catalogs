package com.springkotlin.catalogs.services

import com.springkotlin.catalogs.dtos.CourseDTO
import com.springkotlin.catalogs.entities.Course
import com.springkotlin.catalogs.exceptions.CourseException
import com.springkotlin.catalogs.repositories.CourseRepository
import com.springkotlin.catalogs.repositories.implementation.ICourseService
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository): ICourseService {
    companion object: KLogging()
    override fun add(courseDTO: CourseDTO): CourseDTO {
        val courseEntity = courseDTO.let { Course(null, it.name, it.category) }
        courseRepository.save(courseEntity)
        return courseEntity.let {
            CourseDTO(it.id, it.name, it.category)
        }

    }

    override fun update(id: Int, courseDTO: CourseDTO): CourseDTO {
        val courseEntity = this.courseRepository.findById(id)

        return if (courseEntity.isPresent) {
            courseEntity.get().let {
                it.name = courseDTO.name
                it.category = courseDTO.category

                this.courseRepository.save(it)

                CourseDTO(
                    it.id,
                    it.name,
                    it.category
                )
            }
        }else {
            throw CourseException("No Course is found")
        }
    }

    override fun getAll(): List<CourseDTO> {
        return this.courseRepository.findAll().map{
            CourseDTO(it.id, it.name, it.category)
        }
    }

    override fun delete(id: Int) {
        return this.courseRepository.deleteById(id)
    }
}