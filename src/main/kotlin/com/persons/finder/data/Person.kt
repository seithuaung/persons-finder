package com.persons.finder.data

import javax.persistence.*

@Entity
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = "",
    @ElementCollection
    @CollectionTable(joinColumns = [JoinColumn(name = "reference_id")])
    val locations: List<Location> = ArrayList<Location>()

)