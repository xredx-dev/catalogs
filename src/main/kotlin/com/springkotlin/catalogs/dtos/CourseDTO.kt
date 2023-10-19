package com.springkotlin.catalogs.dtos

import com.springkotlin.catalogs.common.TypeCourse

data class CourseDTO(
    val id :Int?,
    val name : String,
    val category : TypeCourse
)
