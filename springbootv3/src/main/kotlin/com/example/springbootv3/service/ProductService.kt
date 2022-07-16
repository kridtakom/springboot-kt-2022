package com.example.springbootv3.service

import com.demokt.springbootkt.model.web.CreateOrUpdateProductRequest
import com.example.springbootv3.model.Product
import com.example.springbootv3.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.Optional
import javax.persistence.EntityNotFoundException

@Service
class ProductService(
    val productRepository: ProductRepository,
    val studentService: StudentService
) {
    fun getProduct(uuid: UUID): Optional<Product> {
        return productRepository.findById(uuid)
    }

    fun getAllProduct(): MutableList<Product> {
        return productRepository.findAll()
    }

    fun createProduct(prod: CreateOrUpdateProductRequest): Product {
        val std = studentService.getStudent(prod.stdId!!)
        if (!std.isPresent) {
            throw EntityNotFoundException("Not found student ${prod.stdId}")
        }

        return productRepository.save(
            Product(
                std.get(),
                null,
                prod.name!!,
                prod.description!!,
                prod.price!!,
                null,
                null
            )
        )
    }

    fun updateProduct(id: UUID, prod: CreateOrUpdateProductRequest): Product {
        val oldProdOptional = getProduct(id)
        if (!oldProdOptional.isPresent) {
            throw EntityNotFoundException("Not found product $id")
        }
        val oldProd = oldProdOptional.get()
        oldProd.name = prod.name ?: oldProd.name
        oldProd.description = prod.description ?: oldProd.description
        oldProd.price = prod.price ?: oldProd.price
        if (prod.stdId != null) {
            val s = studentService.getStudent(prod.stdId!!)
            if (s.isPresent) oldProd.student = s.get()
        }
        return productRepository.save(oldProd)
    }

    fun deleteProduct(id: UUID) = productRepository.deleteById(id)
}