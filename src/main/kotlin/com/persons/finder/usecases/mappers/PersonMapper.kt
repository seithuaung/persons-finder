package com.persons.finder.usecases.mappers

import com.persons.finder.domain.data.Person
import com.persons.finder.dto.PersonDto

class PersonMapper {

    companion object{

        fun  maptoDto(persons: List<Person?>) : List<PersonDto> {
        val personDtos = mutableListOf<PersonDto>()
        persons.forEach {
            var personDto = it?.let { it1 -> PersonDto(it1.id, it.name) }
            if (personDto != null) {
                personDtos.add(personDto)
            }
        }
        return personDtos
        }

    }
}
