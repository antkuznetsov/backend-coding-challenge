package com.up42.codingchallenge.feature.repository

import com.up42.codingchallenge.feature.model.Feature

interface FeatureRepository {
    fun findAll(): List<Feature>
}
