package com.persons.finder.data

import javax.persistence.*

@Embeddable
data class Location(

    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
