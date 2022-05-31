package com.up42.codingchallenge.feature

import com.up42.codingchallenge.feature.repository.FeatureRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FeatureService(private val repository: FeatureRepository) {
    fun getAllFeatures() = repository.findAll()

    fun getFeature(id: UUID) = repository.findById(id)
}
