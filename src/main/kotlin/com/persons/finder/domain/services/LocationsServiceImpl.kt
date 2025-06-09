package com.persons.finder.domain.services

import com.persons.finder.domain.data.Location
import com.persons.finder.domain.repository.LocationRepository
import com.persons.finder.domain.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service

@Service
class LocationsServiceImpl @Autowired constructor(private val locationRepository: LocationRepository) : LocationsService {

    override fun addLocation(location: Location) {
        locationRepository.save(location)
    }

    override fun removeLocation(locationReferenceId: Long) {
        val location = locationRepository.findById(locationReferenceId)
        locationRepository.delete(location.orElseThrow())
    }

    override fun findAround(latitude: Double, longitude: Double, radiusInKm: Double): List<Location> {
        TODO("Not yet implemented")
    }

    override fun findWithinBoundingBox( latMin: Double,
                                         latMax: Double,
                                         lonMin: Double,
                                         lonMax: Double): List<Location> {
     return locationRepository.findWithinBoundingBox(latMin,latMax,lonMin, lonMax)
    }


}