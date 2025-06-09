package com.persons.finder.domain.repository

import com.persons.finder.domain.data.Location
import com.persons.finder.domain.data.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l " +
            "WHERE l.latitude BETWEEN :latMin AND :latMax " +
            "AND l.longitude BETWEEN :lonMin AND :lonMax")
    fun  findWithinBoundingBox(
        @Param("latMin")  latMin: Double,
        @Param("latMax")  latMax: Double,
        @Param("lonMin")  lonMin: Double,
        @Param("lonMax")  lonMax: Double
    ) : List<Location>
}