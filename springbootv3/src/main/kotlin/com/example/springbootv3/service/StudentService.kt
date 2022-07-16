package com.example.springbootv3.service


import com.demokt.springbootkt.model.web.CreateStudentRequest
import com.demokt.springbootkt.model.web.UpdateStudentRequest
import com.example.springbootv3.model.Product
import com.example.springbootv3.model.Student
import com.example.springbootv3.repository.ProductRepository
import com.example.springbootv3.repository.StudentRepository
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.Optional
import javax.persistence.EntityNotFoundException

@Service
class StudentService(
    val studentRepository: StudentRepository,
    val productRepository: ProductRepository,
) {
    fun getStudent(uuid: UUID): Optional<Student> {
        return studentRepository.findById(uuid)
    }

    fun getAllStudent(): List<Student> {
        return studentRepository.findAll()
    }

    fun createStudent(stdReq: CreateStudentRequest): Student {
        val newStd = studentRepository.save(Student(null, stdReq.name, stdReq.email, stdReq.dob, null))
        if (stdReq.products?.isNotEmpty() == true) {
            val pList: MutableList<Product> = mutableListOf()
            stdReq.products!!.map {
                pList.add(
                    productRepository.save(
                        Product(
                            newStd,
                            null,
                            it.name!!,
                            it.description!!,
                            it.price!!,
                            null,
                            null
                        )
                    )
                )
            }
            newStd.products = pList
        }
        return newStd
    }

    fun updateStudent(id: UUID, std: UpdateStudentRequest): Student {
        val oldStdOptional = getStudent(id)
        if (!oldStdOptional.isPresent) {
            throw EntityNotFoundException("Not found student $id")
        }
        val oldStd = oldStdOptional.get()
        oldStd.dob = if (std.dob != null) std.dob else oldStd.dob
        oldStd.email = if (std.email != null) std.email.toString() else oldStd.email
        oldStd.name = if (std.name != null) std.name.toString() else oldStd.name
        return studentRepository.save(oldStd)
    }

    fun deleteStudent(id: UUID) = studentRepository.deleteById(id)

}