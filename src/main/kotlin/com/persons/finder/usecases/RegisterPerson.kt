package com.persons.finder.usecases

import com.persons.finder.domain.data.Person
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.dto.PersonDto
import com.persons.finder.dto.PersonIdsDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegisterPerson @Autowired constructor(private val personService: PersonsService) {

    fun register(personDto : PersonDto) : PersonDto {
        var person = Person();
        person.name = personDto.name

        person = personService.save(person)
        personDto.id = person.id
        return personDto
    }


}