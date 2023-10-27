package edu.utvt.catalogs.services

import com.springkotlin.catalogs.repositories.implementation.IStudentService
import com.springkotlin.catalogs.dtos.StudentDTO
import com.springkotlin.catalogs.entities.Student
import com.springkotlin.catalogs.exceptions.StudentException
import com.springkotlin.catalogs.repositories.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(val studentRepository: StudentRepository): IStudentService {
    override fun add(studentDTO: StudentDTO): StudentDTO {
        val student = Student(studentDTO.id, studentDTO.name, studentDTO.code)
        studentRepository.save(student);

        return StudentDTO(studentDTO.id, studentDTO.name, studentDTO.code)
    }

    override fun update(id: Int, studentDTO: StudentDTO): StudentDTO {
        val updateStudent = this.studentRepository.findById(id);

        return if (updateStudent.isPresent) {
            updateStudent.get().let {
                it.name = studentDTO.name;
                it.code = studentDTO.code;

                this.studentRepository.save(it);

                StudentDTO(
                    it.id,
                    it.name,
                    it.code
                );
            }
        }
        else {
            throw StudentException("No Course is found");
        }
    }

    override fun getAll(): List<StudentDTO> {
        return this.studentRepository.findAll().map{
            StudentDTO(it.id, it.name, it.code)
        }
    }

    override fun delete(id: Int) {
        return this.studentRepository.deleteById(id);
    }
}