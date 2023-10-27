package com.springkotlin.catalogs.controllers
import com.springkotlin.catalogs.dtos.StudentDTO
import com.springkotlin.catalogs.repositories.implementation.IStudentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/students")


class StudentController(val studentService: IStudentService) {

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun index(): List<StudentDTO>
    {
        return studentService.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody student: StudentDTO): StudentDTO
    {
        return studentService.add(student);
    }

    @PatchMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    fun updateCourse(@PathVariable id: Int, @RequestBody student: StudentDTO): StudentDTO
    {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCourse(@PathVariable id: Int)
    {
        return studentService.delete(id);
    }
}