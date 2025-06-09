package com.persons.finder.usecases
import com.persons.finder.domain.data.Location
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.dto.PersonDto
import com.persons.finder.usecases.mappers.PersonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.*
/**
  * 1) To minimise the search overheads, calculate the rectangle box or bounding box from x and y axes using radius
  * 2) within this box, search the nearby points by using Haversine Distance for sphere surface.
    the math for HaversineDistance :

   a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
   c = 2 * atan2( √a, √(1−a) )
   d = R ⋅ c
   Where:
   R is the radius of the sphere (e.g., Earth's radius is 6,371 km)
   φ is latitude, λ is longitude
   Δφ = φ2 - φ1 (difference in latitude)
   Δλ = λ2 - λ1 (difference in longitude)

 */
@Service
class FindNearbyPerson @Autowired constructor(private val personService: PersonsService,
                                              private val locationService: LocationsService
) {

    fun getNearbyPersons(latitude : Double, longitude: Double, radius: Double) : List<PersonDto> {
       val locations = searchWithinRadius(latitude,longitude,radius)
       val persons =  locations.map {
            it.person
        }
        return PersonMapper.maptoDto(persons)
    }

    fun searchWithinRadius(
        centerLatitude: Double,
        centerLongitude: Double,
        radiusKm: Double
    ): List<Location> {

        // Latitude bounds (~111 km per degree)

        val latMin = centerLatitude - radiusKm / 111.0
        val latMax = centerLatitude + radiusKm / 111.0

        // Longitude bounds (adjusted by latitude)
        val lonDelta = radiusKm / (111.320 * cos(Math.toRadians(centerLatitude)))
        val lonMin = centerLongitude - lonDelta
        val lonMax = centerLongitude + lonDelta

        // Step 1: Bounding box filter
        val candidates = locationService.findWithinBoundingBox(latMin, latMax, lonMin, lonMax)
        println(candidates.size)

        // Step 2: Precise haversine check and sort by distance and return list of people with radius
       return candidates.associate {
            (getHaversineDistance(
                it.latitude,
                it.longitude,
                centerLatitude,
                centerLongitude)) to it
        }.toSortedMap().filter {
           it.key <= radiusKm
       }.values.toList()
    }

    fun getHaversineDistance(latitude1: Double,
                             longitude1: Double,
                             latitude2: Double,
                             longitude2: Double): Double {
        val R = 6371.0  // Radius of Earth in km
        val dLat = Math.toRadians(latitude2 - latitude1)
        val dLon = Math.toRadians(longitude2 - longitude1)
        val a = sin(dLat / 2).pow(2.0) +
                cos(Math.toRadians(latitude1)) * cos(Math.toRadians(latitude2)) *
                sin(dLon / 2).pow(2.0)
        return 2 * R * asin(sqrt(a))
    }
}
