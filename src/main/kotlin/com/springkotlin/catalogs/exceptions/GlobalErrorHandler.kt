package com.springkotlin.catalogs.exceptions

import com.springkotlin.catalogs.models.ErrorMessageModel
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.http.HttpHeaders

@RestControllerAdvice
class GlobalErrorHandler:ResponseEntityExceptionHandler() {
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request : WebRequest
    ): ResponseEntity<Any>{
        logger.error("handleMethodArgumentNotValid observed: ${ex.message}",ex)
        logger.error("WebRequest observed : $request")

        val errors = ex.bindingResult.allErrors
            .map { error -> error.defaultMessage!! }
            .sorted()
        val errorMessageModel = ErrorMessageModel(status.value(), errors.joinToString(", "),request.toString())

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorMessageModel)
    }
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request:WebRequest): ResponseEntity<Any>{
        logger.error("Exception observed: ${ex.message}", ex)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.message)
    }
}