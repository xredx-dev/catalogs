package edu.utvt.catalogs.services

import com.springkotlin.catalogs.dtos.CourseDTO
import com.springkotlin.catalogs.entities.Course
import com.springkotlin.catalogs.exceptions.CourseException
import com.springkotlin.catalogs.repositories.CourseRepository
import com.springkotlin.catalogs.repositories.implementation.ICourseService
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository): ICourseService {

    override fun add(courseDto: CourseDTO): CourseDTO
    {
        val course = Course(courseDto.id, courseDto.name, courseDto.category)
        courseRepository.save(course);

        return CourseDTO(course.id, course.name, course.category)
    }

    override fun update(id: Int, courseDTO: CourseDTO): CourseDTO {
        val updateCourse = this.courseRepository.findById(id);

        return if (updateCourse.isPresent) {
            updateCourse.get().let {
                it.name = courseDTO.name;
                it.category = courseDTO.category;

                this.courseRepository.save(it);

                CourseDTO(
                    it.id,
                    it.name,
                    it.category
                );
            }
        }
        else {
            throw CourseException("No Course is found");
        }
    }

    override fun getAll(): List<CourseDTO> {
        return this.courseRepository.findAll().map{
            CourseDTO(it.id, it.name, it.category)
        }
    }

    override fun delete(id: Int) {
        return this.courseRepository.deleteById(id);
    }
}