package com.up42.codingchallenge.feature.exception

import java.util.UUID

class FeatureNotFoundException(id: UUID) : RuntimeException("Could not find the feature $id")
