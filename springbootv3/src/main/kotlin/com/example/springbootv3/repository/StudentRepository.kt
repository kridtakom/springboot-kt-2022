package com.example.springbootv3.repository

import com.example.springbootv3.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface StudentRepository : JpaRepository<Student, UUID> {
}