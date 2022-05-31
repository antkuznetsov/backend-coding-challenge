package com.up42.codingchallenge.feature.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FeatureCollection(var features: List<Feature>)
