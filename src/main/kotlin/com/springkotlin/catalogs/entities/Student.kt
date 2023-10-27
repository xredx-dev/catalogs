package com.springkotlin.catalogs.entities

import jakarta.persistence.*

@Entity
@Table(name = "te_students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var code: String
)
