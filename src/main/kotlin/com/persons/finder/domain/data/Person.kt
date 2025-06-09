package com.persons.finder.domain.data

import javax.persistence.*

@Entity
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "",
//    @ElementCollection
//    @CollectionTable(joinColumns = [JoinColumn(name = "reference_id")])

    @OneToOne( cascade =[ CascadeType.ALL ])
    @PrimaryKeyJoinColumn
    val location: Location? = null

)