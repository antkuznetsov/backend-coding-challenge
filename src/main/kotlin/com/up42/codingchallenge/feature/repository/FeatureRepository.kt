package com.up42.codingchallenge.feature.repository

import com.up42.codingchallenge.feature.model.Feature
import java.util.*

interface FeatureRepository {
    fun findAll(): List<Feature>

    fun findById(id: UUID): Feature?
}
