package com.up42.codingchallenge.feature

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FeatureController (private val service: FeatureService) {
    @GetMapping("/features")
    fun getFeatures() = service.getAllFeatures()
}
