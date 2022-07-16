package com.demokt.springbootkt.model.web

import java.time.LocalDate

data class CreateStudentRequest(
    val name: String,
    var email: String,
    var dob: LocalDate?,
    var products: List<CreateOrUpdateProductRequest>?
)