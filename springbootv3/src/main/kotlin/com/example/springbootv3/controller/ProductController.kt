package com.example.springbootv3.controller

import com.demokt.springbootkt.model.web.CreateOrUpdateProductRequest
import com.example.springbootv3.service.ProductService
import com.example.springbootv3.model.Product
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("products")
class ProductController(val productService: ProductService) {

    @GetMapping
    fun getAllProduct(): ResponseEntity<MutableList<Product>> {
        return ResponseEntity(productService.getAllProduct(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: String): ResponseEntity<Optional<Product>> {
        return ResponseEntity(productService.getProduct(UUID.fromString(id)), HttpStatus.OK)
    }

    @PostMapping
    fun createProduct(@RequestBody prod: CreateOrUpdateProductRequest): ResponseEntity<Product> {
        return ResponseEntity(productService.createProduct(prod), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: String, @RequestBody prod: CreateOrUpdateProductRequest): ResponseEntity<Product> {
        return ResponseEntity(productService.updateProduct(UUID.fromString(id), prod), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: String): ResponseEntity<String> {
        productService.deleteProduct(UUID.fromString(id))
        return ResponseEntity("Delete product $id success", HttpStatus.OK)
    }
}