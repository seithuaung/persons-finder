package com.persons.finder.domain.services

import com.persons.finder.domain.data.Person
import com.persons.finder.domain.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonsServiceImpl @Autowired constructor(private val personRepository: PersonRepository) : PersonsService {

    override fun getById(id: Long): Person {
       return personRepository.findById(id).orElse(null)
    }

    override fun getByIds(ids: Set<Long>): List<Person> {
        return personRepository.findAllById(ids)
    }

    override fun save(person: Person): Person {
       return personRepository.save(person)
    }

}