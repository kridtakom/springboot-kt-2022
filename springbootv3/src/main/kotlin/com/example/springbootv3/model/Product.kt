package com.example.springbootv3.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(

    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST], fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "stdId")
    @JsonBackReference
    var student: Student,

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    val id: UUID?,

    var name: String,

    @Column(columnDefinition = "MEDIUMTEXT")
    var description: String,

    var price: Double = 0.0,

    @CreationTimestamp
    var createdAt: LocalDateTime?,

    @UpdateTimestamp
    var updatedAt: LocalDateTime?,
)
