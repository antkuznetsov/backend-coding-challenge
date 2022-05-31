package com.up42.codingchallenge

import com.up42.codingchallenge.feature.repository.FeatureRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FeaturesController (private val repository: FeatureRepository) {
    @GetMapping("/features")
    fun getFeatures() = repository.findAll()
}
