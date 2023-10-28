package com.springkotlin.catalogs.models

data class ErrorMessageModel(
    val status : Int? = null,
    val message : String? = null,
    val path : String? = ""
)
