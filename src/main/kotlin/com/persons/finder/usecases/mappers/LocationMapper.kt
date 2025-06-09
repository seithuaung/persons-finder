package com.persons.finder.usecases.mappers

import com.persons.finder.domain.data.Location
import com.persons.finder.domain.data.Person
import com.persons.finder.dto.LocationDto

class LocationMapper {
    companion object{
        fun mapToEntity(locationDto: LocationDto, person: Person) : Location {
            var location = Location(0, person,
                locationDto.latitude,
                locationDto.longitude)
            return location
        }

        fun mapToDto(location: Location): LocationDto {
            return LocationDto(location.person?.id ?: 0, location.latitude, location.longitude)
        }
    }
}