package com.example.springbootv3.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.time.Period
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "students")
data class Student(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    val id: UUID?,

    var name: String,

    @Column(unique = true)
    var email: String,

    var dob: LocalDate?,

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    var products: List<Product>?

) {
    @JsonGetter("age")
    fun getAge(): Int {
        return if (this.dob != null) {
            Period.between(this.dob, LocalDate.now()).years
        } else {
            0
        }
    }
}