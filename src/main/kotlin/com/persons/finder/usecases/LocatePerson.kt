package com.persons.finder.usecases

import com.persons.finder.domain.repository.LocationRepository
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.dto.PersonLocationDto
import com.persons.finder.usecases.mappers.LocationMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocatePerson @Autowired constructor(private val personService: PersonsService,
private val locationService: LocationsService) {

    fun locatePerson(personLocationDto: PersonLocationDto) : PersonLocationDto {
        var person = personService.getById(personLocationDto.id)
        val location = LocationMapper.mapToEntity(personLocationDto.location,person)
        locationService.addLocation(location)
        return personLocationDto
    }

}