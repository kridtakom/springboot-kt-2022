package com.demokt.springbootkt.model.web

import java.time.LocalDate

data class UpdateStudentRequest(
    val name: String?,
    var email: String?,
    var dob: LocalDate?,
)
