package com.springkotlin.catalogs.controllers
import com.springkotlin.catalogs.dtos.CourseDTO
import com.springkotlin.catalogs.repositories.implementation.ICourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/courses")

class CourseController(val courseServicies: ICourseService) {

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun index(): List<CourseDTO>
    {
        return courseServicies.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody courseDTO: CourseDTO): CourseDTO
    {
        return courseServicies.add(courseDTO);
    }

    @PatchMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    fun updateCourse(@PathVariable id: Int, @RequestBody courseDTO: CourseDTO): CourseDTO
    {
        return courseServicies.update(id, courseDTO);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCourse(@PathVariable id: Int)
    {
        return courseServicies.delete(id);
    }
}