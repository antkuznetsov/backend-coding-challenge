package com.up42.codingchallenge.feature

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import java.util.UUID

@RestController
class FeatureController(private val service: FeatureService) {
    @GetMapping("/features")
    fun getFeatures() = service.getAllFeatures()

    @GetMapping("/features/{id}")
    fun getFeature(@PathVariable id: UUID) =
        service.getFeature(id) ?: throw ResponseStatusException(NOT_FOUND, "Feature does not exist!")
}
