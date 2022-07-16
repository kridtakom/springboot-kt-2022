package com.demokt.springbootkt.model.web

import java.util.UUID

data class CreateOrUpdateProductRequest(
    var name: String?,
    var description: String?,
    var price: Double?,
    var stdId: UUID?
)
