package com.edeesis.micronaut

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity("users")
class User(
    val age: Int,
    val name: String,
    @field:Id
    @field:GeneratedValue
    val id: Int? = null
)