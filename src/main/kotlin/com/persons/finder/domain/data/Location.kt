package com.persons.finder.domain.data

import javax.persistence.*

@Entity
data class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    @MapsId
    @JoinColumn(name = "reference_id")
    val person: Person? = null ,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
