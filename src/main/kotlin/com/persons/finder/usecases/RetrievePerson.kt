package com.persons.finder.usecases

import com.persons.finder.domain.services.PersonsService
import com.persons.finder.dto.PersonDto
import com.persons.finder.usecases.mappers.PersonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RetrievePerson @Autowired constructor(private val personsService: PersonsService) {

    fun retrievePersons(ids : Set<Long>) : List<PersonDto> {
        return PersonMapper.maptoDto(personsService.getByIds(ids)).orEmpty()
    }


}