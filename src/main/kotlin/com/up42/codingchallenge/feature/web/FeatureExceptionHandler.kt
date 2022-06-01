package com.up42.codingchallenge.feature.web

import com.up42.codingchallenge.feature.exception.FeatureNotFoundException
import com.up42.codingchallenge.feature.exception.QuicklookNotFoundException
import com.up42.codingchallenge.feature.web.dto.FeatureErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice("com.up42.codingchallenge.feature")
class FeatureExceptionHandler {
    @ExceptionHandler(FeatureNotFoundException::class)
    fun featureNotFoundHandler(ex: FeatureNotFoundException): ResponseEntity<FeatureErrorDto> {
        val error = FeatureErrorDto(HttpStatus.NOT_FOUND.value(), ex.message)
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(QuicklookNotFoundException::class)
    fun quicklookNotFoundHandler(ex: QuicklookNotFoundException): ResponseEntity<FeatureErrorDto> {
        val error = FeatureErrorDto(HttpStatus.NOT_FOUND.value(), ex.message)
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}
