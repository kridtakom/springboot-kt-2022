package com.example.springbootv3.controller

import com.demokt.springbootkt.model.web.CreateStudentRequest
import com.demokt.springbootkt.model.web.UpdateStudentRequest
import com.example.springbootv3.model.Student
import com.example.springbootv3.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("students")
class StudentController(val studentService: StudentService) {

    @GetMapping
    fun getAllStudent(): ResponseEntity<List<Student>> {
        return ResponseEntity(studentService.getAllStudent(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: String): ResponseEntity<Optional<Student>> {
        return ResponseEntity(studentService.getStudent(UUID.fromString(id)), HttpStatus.OK)
    }

    @PostMapping
    fun createStudent(@RequestBody stdReq: CreateStudentRequest): ResponseEntity<Student> {
        return ResponseEntity(studentService.createStudent(stdReq), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: String, @RequestBody std: UpdateStudentRequest): ResponseEntity<Student> {
        return ResponseEntity(studentService.updateStudent(UUID.fromString(id), std), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: String): ResponseEntity<String> {
        studentService.deleteStudent(UUID.fromString(id))
        return ResponseEntity("Delete student $id success", HttpStatus.OK)
    }
}