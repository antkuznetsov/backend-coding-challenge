package com.up42.codingchallenge.feature

import com.up42.codingchallenge.feature.repository.FeatureRepository
import org.springframework.stereotype.Service

@Service
class FeatureService(private val repository: FeatureRepository) {
    fun getAllFeatures() = repository.findAll()
}
